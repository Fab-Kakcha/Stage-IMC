package codegenerateur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.lang.StringUtils;

import com.imcfr.commun.EnvProperties;
import com.imcfr.commun.MessageManager;
import com.imcfr.commun.ObjectUtils;
import com.imcfr.commun.PropertiesManager;
import com.imcfr.generateur.GenerationException;
import com.imcfr.generateur.GenerationManager;
import com.imcfr.generateur.generator.Api;
import com.imcfr.generateur.generator.ContextProperty;
import com.imcfr.generateur.generator.Generator;
import com.imcfr.generateur.generator.GeneratorException;
import com.imcfr.generateur.generator.GeneratorsLibrary;
import com.imcfr.generateur.generator.GeneratorsManager;
import com.imcfr.generateur.generator.Property;
import com.imcfr.generateur.generator.Support;
import com.imcfr.generateur.generator.TemplateGenerator;
import com.imcfr.generateur.language.Language;
import com.imcfr.generateur.language.LanguageException;
import com.imcfr.generateur.language.LanguageNotFoundException;
import com.imcfr.generateur.language.LanguagesManager;
import com.imcfr.generateur.model.commun.AvailableModels;
import com.imcfr.generateur.model.commun.Dependency;
import com.imcfr.generateur.model.commun.GenericElement;
import com.imcfr.generateur.model.commun.GenericModel;
import com.imcfr.generateur.model.commun.ModelException;
import com.imcfr.generateur.model.commun.ModelsBox;
import com.imcfr.generateur.model.commun.ModelsBoxManager;
import com.imcfr.generateur.validation.ValidatorException;
import com.imcfr.generateur.workspace.Workspace;
import com.imcfr.generateur.workspace.WorkspaceManager;
import com.imcfr.generateur.xmi.XmiManager;
import com.imcfr.generateur.xmi.profile.Profile;
import com.imcfr.generateur.xmi.profile.ProfileNotFoundException;
import com.imcfr.outils.event.BasicConsoleObserver;
import com.imcfr.outils.xml.SaxChargeurException;

/**
 * 
 * @author fkakcha
 *
 */

public class GenerateurCodesSources {

	
	/**
	 * Propriété des workspaces par défaut
	 */
	private static final String CONFIG_FILE = "config/codeGenerator.properties";

	/**
	 * Propriété des workspaces par défaut
	 */
	private static final String CONFIG_XSD_URL = "classpath:com/imcfr/generateur/sax/XsdLocation.properties";

	/**
	 * Clef utilisé pour mettre dans le properties manager, le chemin vers le fichier XsdLocation.properties
	 */
	private static final String CONFIG_XSD_PROP_KEY = "xsd.location.file";

	/**
	 * Propriété des workspaces par défaut
	 */
	private static final String WORKSPACES = "workspaces";

	/**
	 * Propriété des workspaces par défaut
	 */
	private static final String COMMANDES = "commandes";

	/**
	 * Propriété des workspaces par défaut
	 */
	private String workspaces = "";

	/**
	 * chemin vers le fichier de config
	 */
	private String configFile = "";

	/**
	 * chemin vers le fichier de config xsd
	 */
	private String configXsdUrl;

	/**
	 * Manager de génération
	 */
	private GenerationManager manager = null;

	/**
	 * Manager de génération
	 */
	private WorkspaceManager workspaceManager = null;

	/**
	 * l'observer
	 */
	private BasicConsoleObserver observer = new BasicConsoleObserver();

	/**
	 * le group de commande courant
	 */
	private String group = null;

	/**
	 * le pool de commandes
	 */
	private List<String> poolCommandes = new LinkedList<String>();

	/**
	 * la dernière commande faite
	 */
	private String lastCommande = null;

	private Workspace currentWorkspace = null;

	/**
	 * code de retour d'une bonne commande
	 */
	private static final int GOOD_COMMANDE = 0;

	/**
	 * code de retour d'une mauvaise commande
	 */
	private static final int BAD_COMMANDE = 1;

	/**
	 * code de retour d'une commande de stop
	 */
	private static final int STOP_COMMANDE = 2;
	
	@SuppressWarnings("static-access")
	public static Options commandLineDefinition() {
		Options options = new Options();

		options.addOption("h", "help", false, "print this help message");

		options.addOption(OptionBuilder.withArgName("file")
									 .hasArg()
									 .withDescription("chemin vers le fichier de configuration principal du générateur")
									 .withLongOpt("config")
									 .create("c"));

		options.addOption(OptionBuilder.withArgName("file")
									 .hasArg()
									 .withDescription("chemin vers le fichier de configuration des xsd")
									 .withLongOpt("xsd")
									 .create("x"));

		options.addOption(OptionBuilder.withArgName("name")
									 .hasArg()
									 .withDescription("nom du workspace à charger au démarage")
									 .withLongOpt("workspace")
									 .create("w"));

		options.addOption(OptionBuilder.withDescription("indique si on ne veut pas lancer les précommandes au lancement")
									 .withLongOpt("no-precmd")
									 .create("np"));

		options.addOption(OptionBuilder.withArgName("type>=<file")
									 .hasArgs(2)
									 .withValueSeparator()
									 .withDescription("type du model contenu dans le fichier indiqué")
									 .withLongOpt("model")
									 .create("m"));

		options.addOption(OptionBuilder.withDescription("indique si on ne veut pas valider le model passer en paramètre")
									 .withLongOpt("no-validate-model")
									 .create("nvm"));

		options.addOption(OptionBuilder.withArgName("file")
									 .hasArg()
									 .withDescription("chemin vers un fichier de commande à exécuter (équivalent à l'utilisation d'une redirection d'entrée '< file'")
									 .withLongOpt("execute")
									 .create("e"));

		return options;
	}

	/**
	 * Initialise le moteur
	 */
	public void init() {
		currentWorkspace = null;

		// on créer le manager
		manager = new GenerationManager();
		manager.addObserver(observer);

		// on crée le manager de générateurs
		GeneratorsManager gManager = new GeneratorsManager();
		gManager.addObserver(observer);
		manager.setGeneratorsManager(gManager);

		// on crée le manager de profiles
		XmiManager pManager = new XmiManager();
		pManager.addObserver(observer);
		manager.setXmiManager(pManager);

		// on crée le manager de languages
		LanguagesManager lManager = new LanguagesManager();
		lManager.addObserver(observer);
		manager.setLanguagesManager(lManager);

		// on crée le manager de box
		ModelsBoxManager mbManager = new ModelsBoxManager();
		manager.setModelsBoxesManager(mbManager);

		initPoolCommandes();
	}

	public void loadConfig(CommandLine cmd) {
		// chargement env properties
		PropertiesManager.getInstance().set(EnvProperties.PROP_ENCODAGE_FICHIER, "UTF-8");
		PropertiesManager.getInstance().set(EnvProperties.PROP_PAYS, "FR");
		PropertiesManager.getInstance().set(EnvProperties.PROP_LANGUE, "fr");
		try {
			EnvProperties.getInstance();
		} catch (RuntimeException e) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class, "Err.InitGenerateur"));
			System.exit(-1);
		}


		// on prend la valeur du config file dans la ligne de commande et si l'option n'est pas présente on prend la constante
		configFile = cmd.getOptionValue("config", CONFIG_FILE);
		// on charge le fichier de propriétés si il existe
		PropertiesManager.getInstance().loadFile(configFile);

		// chargement du fichier XsdLocation.properties
		// on prend la valeur du xsd dans la ligne de commande et si l'option n'est pas présente on regarde dans le properties manager
		// enfin si elle n'est toujours pas présente on prends la constante
		configXsdUrl = cmd.getOptionValue("xsd", PropertiesManager.getInstance().get(CONFIG_XSD_PROP_KEY));
		if (ObjectUtils.isNull(configXsdUrl)) {
			configXsdUrl = CONFIG_XSD_URL;
		}
		// on charge le fichier de propriétés des xsd
		PropertiesManager.getInstance().loadFile(configXsdUrl);
	}

	/**
	 * Lancement de l'application
	 */
	public void launch(CommandLine cmd) {
		loadConfig(cmd);

		// initialiation du scanner de l'input
		Scanner scanner = null;
		// si on a un fichier spécifier on le prend à la place de stdin
		if (cmd.hasOption("execute")) {
			File executedFile = new File(cmd.getOptionValue("execute"));
			if (executedFile != null && executedFile.exists()) {
				try {
					scanner = new Scanner(executedFile);
				} catch (FileNotFoundException e) {
					// si on arrive pas à lire le fichier on fait comme si il n'y en avait pas
					scanner = new Scanner(System.in);
				}
			}
		} else {
			scanner = new Scanner(System.in);
		}

		observer = new BasicConsoleObserver();

		// on init le GenerateurCodesSources
		init();

		// on charge les workspace
		load();

		// Affichage info
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Hello"));
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Hello.OptionConfig", new String[]{ configFile }));
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Hello.OptionXSD", new String[]{ configXsdUrl }));
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Hello2"));

		int code = GOOD_COMMANDE;

		// gestion des precommandes generateur
		boolean launchPreCommand = true;
		if (cmd.hasOption("no-precmd")) {
			launchPreCommand = false;
		}
		if (launchPreCommand) {
			code = launchPreCommands();
		}

		if (code != STOP_COMMANDE) {
			// on charge le workspace demandé en ligne de commande
			String workspaceToLoad = cmd.getOptionValue("workspace", null);
			if (ObjectUtils.isNotNull(workspaceToLoad)) {
				code = workspaceLoad(workspaceToLoad, launchPreCommand);
			}
		}

		if (code != STOP_COMMANDE && cmd.hasOption("model")) {
			String[] modelOption = cmd.getOptionValues("model");
			if (modelOption != null && modelOption.length == 2) {
				code = loadModel(modelOption[0], modelOption[1], cmd.hasOption("no-validate-model"));
			}
		}


		// boucle de commande tant que l'on ne quitte pas
		String ligneCommande = "";

		while (code != STOP_COMMANDE) {
			System.out.print(">");
			System.out.flush();

			try {
				ligneCommande = scanner.nextLine();

				if (ligneCommande.equals("$>")) {
					System.out.println("\tVous pouvez saisir la commande de votre choix.\n\t - exit : pour arrêter l'exécution\n\t - ou aucune commande (touche [Entrée]) pour continuer");
					System.out.println(">");
					System.out.flush();
					Scanner scannerPrompt = new Scanner(System.in);
					ligneCommande = scannerPrompt.nextLine();
				}

				code = traiterCommandes(ligneCommande);

				// si commande ok et dif de last et last print on la conserve
				if (code == GOOD_COMMANDE
						&& ligneCommande.equals("last") == false
						&& ligneCommande.equals("last print") == false) {
					lastCommande = ligneCommande;
				}
			} catch (NoSuchElementException e) {
				// c'est que nous étions en train de lire une redirection de flux et que l'on est arrivé sur eof
				// donc on arrête le generateur
				code = STOP_COMMANDE;
			}
		}

		save();

		System.out
				.print(MessageManager.get(GenerateurCodesSources.class, "Info.Exit") + "\n");
	}

	public void initPoolCommandes() {
		poolCommandes = new LinkedList<String>();
		String commandes = PropertiesManager.getInstance().get(COMMANDES);
		if (ObjectUtils.isNotNull(commandes)) {
			for (String s : commandes.split(",")) {
				poolCommandes.add(s);
			}
		}
	}

	public int launchPreCommands(){
		// on récup les commandes définies
		return execPreCommands(poolCommandes);
	}

	public int execPreCommands(List<String> poolCommandes) {
		int code= GOOD_COMMANDE;
		if (ObjectUtils.isNotNull(poolCommandes)) {

			// on crée le pool
			for (String s : poolCommandes) {
				if(code==GOOD_COMMANDE){
					code=traiterCommandes(s);
				}
			}
		}
		return code;
	}

	/**
	 * Charge les workspace de librairie définit par défaut
	 */
	public void load() {
		// on récup les workspaces définit
		workspaces = PropertiesManager.getInstance().get(WORKSPACES);

		// on créer le manager d'archives
		workspaceManager = new WorkspaceManager();
		workspaceManager.addObserver(observer);

		if (workspaces != null && workspaces.equals("") == false) {

			for (String workspace : workspaces.split(",")) {
				if (workspace.split(";").length == 2) {
					String[] wkParts = workspace.split(";");
					if (wkParts.length == 2) {
						workspaceManager.addWorkspace(wkParts[0], wkParts[1]);
					}
				}
			}
		} else {
			workspaces = "";
		}

		for (Workspace w : workspaceManager.getWorkspacesList()) {
			w.addObserver(observer);
		}
	}

	/**
	 * Charge les workspace de librairie définit par défaut
	 */
	public void save() {
		for (Workspace wk : workspaceManager.getWorkspacesList()) {
			// on fait expres de mettre un array vide, pour dire que l'on ne veut rien sauvagarder du fichier de conf
			// car si on met null, cela veut dire tout
			// on garde l'appel au save, pour le cas où le workspace aurait des traitements spécifique à faire à l'arret du generateur
			wk.save(new String[] { });
		}

		saveGenerateur(new String[] { WORKSPACES });
	}

	public void saveGenerateur(String[] propToSave) {
		if (ObjectUtils.isNull(propToSave)) {
			saveGenerateur((List<String>)null);
		} else {
			saveGenerateur(Arrays.asList(propToSave));
		}
	}
	
	public void saveGenerateur(List<String> propToSave) {
		if (ObjectUtils.isNull(propToSave)) {
			propToSave = new ArrayList<String>();
			propToSave.add(WORKSPACES);
			propToSave.add(COMMANDES);
		}

		Properties prop = new Properties();
		File fichierPropInitial = new File (configFile);
		try {
			// si le fichier n'existe pas on le cré
			if (fichierPropInitial.exists() == false) {
				fichierPropInitial.createNewFile();
			}
			FileReader reader = new FileReader(fichierPropInitial);
			prop.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			return;
		}

		if (propToSave.contains(WORKSPACES)) {
			prop.setProperty(WORKSPACES, workspaces);
		}

		if (propToSave.contains(COMMANDES)) {
			String commandes = "";
			for (String s : poolCommandes) {
				if (commandes.equals("") == false) {
					commandes += ",";
				}
				commandes += s;
			}
			prop.setProperty(COMMANDES, commandes);
		}

		try {
			FileOutputStream out = new FileOutputStream(configFile);
			prop.store(out, "\nfichier de configuration du générateur\nles clefs possible sont :\n\t"+WORKSPACES+" : permet d'indiquer la liste des workspaces disponibles sous le format <nom>;<chemin>,...\n\t"+COMMANDES+" : permet d'indiquer une liste de précommandes pour la lancement du générateur\n\t"+CONFIG_XSD_PROP_KEY+" : permet d'indiquer le chemin vers le fichier de configuration du mapping des namespace xsd\n");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	public int loadModel(String type, String file, boolean noValidation) {
		if (currentWorkspace == null) {
			System.err.println("Vous devez avoir chargé un workspace pour pouvoir utiliser l'option --model, soit grâce à une pré-commande, soit par l'option --workspace");
			return STOP_COMMANDE;
		}

		try {
			List<String> typesAvailables = AvailableModels.getModels();

			if (typesAvailables == null || typesAvailables.contains(type) == false) {
				System.err.println("Impossible de charger le model "+file+" car le type "+type+" n'est pas valide");
				return STOP_COMMANDE;
			} else {
				File modelFile = new File(file);
				if (modelFile.exists() == false) {
					System.err.println("Impossible de charger le model "+file+" car le fichier n'existe pas");
					return STOP_COMMANDE;
				} else {

					// création d'une boite virtuel
					ModelsBox box = new ModelsBox("VirtualBox_"+type);
					box.addModel(type, file);

					manager.getModelsBoxesManager().addBox(box);

					// lancement du chargement du model
					if (noValidation == false) {
						languageValidate();
						profileValidate();

						modelValidate("VirtualBox_"+type);
					}

				}
			}
		} catch (ModelException e) {
			// rien
		}

		return GOOD_COMMANDE;
	}

	/**
	 * Indique si une commande est group
	 *
	 * @param name
	 *            le nom de la commande
	 *
	 * @return true ou false
	 */
	public boolean isGroupCommande(String name) {
		String[] groups = new String[] { "group", "commandes", "workspace",
				"language", "library", "profile", "project", "pathGeneration" };
		boolean res = false;

		for (String s : groups) {
			if (name.equals(s)) {
				res = true;
				break;
			}
		}

		return res;
	}

	/**
	 * Indique si une commande est basic ou group
	 *
	 * @param name
	 *            le nom de la commande
	 *
	 * @return true ou false
	 */
	public boolean isBasicCommande(String name) {
		String[] groups = new String[] { "help", "clear", "exit", "last" };
		boolean res = false;

		for (String s : groups) {
			if (name.equals(s)) {
				res = true;
				break;
			}
		}

		if (res == false) {
			res = isGroupCommande(name);
		}

		return res;
	}

	/**
	 * Traite une ligne de commande
	 *
	 * @param commande
	 *            la ligne de commande
	 *
	 * @return vrai pour quitter, faux si continuer
	 */
	public int traiterCommandes(String commande) {
		int code = BAD_COMMANDE;

		try {
			if (commande.length() != 0) {
				if (commande.startsWith("#")) {
					if (commande.startsWith("##") == false) {
						System.out.println(commande.substring(1));
					}
					return GOOD_COMMANDE;
				}

				String[] params = null;
				String[] tpParams = commande.split(" ");
				String curGroup = group;

				// si le group courant est null et que le premier param est pas
				// un group
				if (curGroup == null || isBasicCommande(tpParams[0])) {
					curGroup = tpParams[0];

					params = new String[tpParams.length - 1];
					for (int i = 1; i < tpParams.length; i++) {
						params[i - 1] = tpParams[i];
					}
				} else {
					params = tpParams;
				}

				if (params.length == 0) {
					if (curGroup.equals("exit")) {
						code = STOP_COMMANDE;
					} else if (curGroup.equals("clear")) {
						init();
						code = GOOD_COMMANDE;

					} else if (curGroup.equals("help")) {
						help();
						code = GOOD_COMMANDE;

					} else if (curGroup.equals("last")) {
						traiterLast();
						code = GOOD_COMMANDE;

					} else if (curGroup.equals("info")) {
						info();
						code = GOOD_COMMANDE;
					}

				} else if (params.length >= 1) {
					if (curGroup.equals("help")) {
						if (params.length == 1) {
							help(params[0]);
							code = GOOD_COMMANDE;
						}
					} else if (curGroup.equals("last")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								printLast();
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("group")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								groupPrint();
								code = GOOD_COMMANDE;
							} else if (params[0].equals("clear")) {
								groupClear();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("set")) {
								groupSet(params[1]);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("commande")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								commandesPrint();
								code = GOOD_COMMANDE;
							} else if (params[0].equals("execute")) {
								commandesExecute();
								code = GOOD_COMMANDE;
							} else if (params[0].equals("clear")) {
								commandesClear();
								code = GOOD_COMMANDE;
							} else if (params[0].equals("save")) {
								commandesSave();
								code = GOOD_COMMANDE;
							}
						} else if (params.length == 2) {
							if (params[0].equals("print")) {
								commandesPrint(params[1]);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("execute")) {
								commandesExecute(params[1]);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("clear")) {
								commandesClear(params[1]);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("save")) {
								commandesSave(params[1]);
								code = GOOD_COMMANDE;
							}
						} else {
							if (params[0].equals("add")) {
								commandesAdd(params);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("workspace")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								workspacePrint();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("load")) {
								workspaceLoad(params[1]);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("delete")) {
								workspaceDelete(params[1], false);
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 3) {
							if (params[0].equals("load") && params[1].equals("-np")) {
								workspaceLoad(params[1], false);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("create")) {
								workspaceCreate(params[1], params[2]);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("import")) {
								workspaceImport(params[1], params[2]);
								code = GOOD_COMMANDE;
							} else if (params[0].equals("delete") && params[2].equals("-wc")) {
								workspaceDelete(params[1], true);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("language")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								languagePrint();
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("validate")) {
								languageValidate();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("printlang")) {
								languagePrint(params[1]);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("library")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								libraryPrint();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("printlang")) {
								libraryPrintLanguage(params[1]);
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("printgens")) {
								libraryPrintGenerators(params[1]);
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 3) {
							if (params[0].equals("load")) {
								libraryLoad(params[1], params[2]);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("profile")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								profilePrint();
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("validate")) {
								profileValidate();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("print")) {
								profilePrint(params[1]);
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 3) {
							if (params[0].equals("load")) {
								profileLoad(params[1], params[2]);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("model")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								modelPrint();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("printbox")) {
								modelPrint(params[1], false);
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("validate")) {
								modelValidate(params[1]);
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 3) {
							if (params[0].equals("printbox")) {
								if (params[1].equals("-e")) {
									modelPrintByElement(params[2], false);
									code = GOOD_COMMANDE;
								} else if (params[1].equals("-d")) {
									modelPrint(params[2], true);
									code = GOOD_COMMANDE;
								}
							}
							if (params[0].equals("printmodel")) {
								projectPrintModel(params[1], params[2]);
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("printelement")) {
								modelPrintElement(params[1], params[2]);
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("load")) {
								modelLoad(params[1], params[2]);
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("validate")) {
								modelValidate(params[1], params[2]);
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 4) {
							if (params[0].equals("printbox")) {
								if (params[1].equals("-e")) {
									if (params[2].equals("-d")) {
										modelPrintByElement(params[3], true);
									}
								} else if (params[1].equals("-d")) {
									if (params[2].equals("-e")) {
										modelPrintByElement(params[3], true);
									}
								}
								code = GOOD_COMMANDE;
							}
							if (params[0].equals("printmodel")
									&& params[1].equals("-e")) {
								projectPrintModelByElement(params[2], params[3]);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("pathGeneration")) {
						if (params.length == 1) {
							if (params[0].equals("print")) {
								pathGenerationPrint();
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("setRoot")) {
								pathGenerationSetRoot(params[1]);
								code = GOOD_COMMANDE;
							}
						}
						if (params.length == 2) {
							if (params[0].equals("set")) {
								pathGenerationSet(params[1]);
								code = GOOD_COMMANDE;
							}
						}

					} else if (curGroup.equals("generate")) {
						if (params.length == 3) {
							// on demande la generation par défaut
							generate(params[0], params[1], params[2], Support.GENERATION, null);
							code = GOOD_COMMANDE;
						} else if (params.length == 4) {
							if (params[3].startsWith("-mode=")) {
								String modeName =params[3].split("=")[1].toUpperCase();
								try {
									generate(params[0], params[1], params[2], Support.valueOf(modeName), null);
									code = GOOD_COMMANDE;
								} catch (IllegalArgumentException e) {
			
									System.err.println(MessageManager.get(GenerateurCodesSources.class,
											"Err.ModeNotExist",
											new String[] { modeName }));
								}
							} else {
								generate(params[0], params[1], params[2], Support.GENERATION, params[3]);
								code = GOOD_COMMANDE;
							}
						} else if (params.length == 5) {
							if (params[3].startsWith("-mode=")) {
								String modeName =params[3].split("=")[1].toUpperCase();
								try {
									generate(params[0], params[1], params[2], Support.valueOf(modeName), params[4]);
									code = GOOD_COMMANDE;
								} catch (IllegalArgumentException e) {
									System.err.println(MessageManager.get(GenerateurCodesSources.class,
											"Err.ModeNotExist",
											new String[] { modeName }));
								}

							} else {
								String modeName =params[4].split("=")[1].toUpperCase();
								try {
									generate(params[0], params[1], params[2], Support.valueOf(modeName), params[3]);
									code = GOOD_COMMANDE;
								} catch (IllegalArgumentException e) {
									System.err.println(MessageManager.get(GenerateurCodesSources.class,
											"Err.ModeNotExist",
											new String[] { modeName }));
								}
							}
						}
					}
				}

				if (code == BAD_COMMANDE ) {
					String sParams = "";
					for (String p : params) {
						if (sParams.equals("") == false) {
							sParams += ", ";
						}
						sParams += p;
					}
					System.err.println(MessageManager.get(GenerateurCodesSources.class,
							"Info.BadCommand",
							new String[] { curGroup, sParams }));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return code;
	}

	/**
	 * Affiche l'aide
	 */
	public void help() {
		help(null);
	}
	public void help(String group) {
		if (group == null) {
			group = "";
		}

		
		if (ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Begin", new String[] { MessageManager.get(
							GenerateurCodesSources.class, "Info.GenerateurVersion") }));
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Begin2"));
			System.out.println("");
		}
	
		if (ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.General"));
		}
		if (group.equals("help") || ObjectUtils.isNull(group)) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Help"));
		}
		if (group.equals("clear") || ObjectUtils.isNull(group)) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Clear"));
		}
		if (group.equals("info") || ObjectUtils.isNull(group)) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Info"));
		}
		if (group.equals("last") || ObjectUtils.isNull(group)) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Last"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.LastPrint"));
		}
		if (ObjectUtils.isNull(group)) {
			System.out.println("");
		}
		
		if (group.equals("group") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager
					.get(GenerateurCodesSources.class, "Info.Help.Group"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Group.Print"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Group.Set"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Group.Clear"));
			System.out.println("");
		}
		
		if (group.equals("commande") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Commandes"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Commandes.Print"));
			System.out
					.println("\t"
							+ MessageManager.get(GenerateurCodesSources.class,
									"Info.Help.Commandes.Add"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Commandes.Execute"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Commandes.Clear"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Commandes.Save"));
			System.out.println("");
		}

		if (group.equals("workspace") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Workspace"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Workspace.Print"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Workspace.Create"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Workspace.Import"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Workspace.Delete"));
			System.out.println("\t"
					+ MessageManager
							.get(GenerateurCodesSources.class, "Info.Help.Workspace.Load"));
			System.out.println("");
		}

		if (group.equals("language") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Language"));
			System.out.println("\t"
					+ MessageManager
							.get(GenerateurCodesSources.class, "Info.Help.Language.Print"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Language.PrintLanguage"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Language.Validate"));
			System.out.println("");
		}

		if (group.equals("library") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Library"));
			System.out
			.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Library.Print"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Library.PrintLanguage"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Library.PrintGenerators"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Library.Load"));
			System.out.println("");
		}

		if (group.equals("profile") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Profile"));
			System.out
					.println("\t"
							+ MessageManager.get(GenerateurCodesSources.class,
									"Info.Help.Profile.Print"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Profile.Load"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Profile.Validate"));
			System.out.println("");
		}

		if (group.equals("model") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager
					.get(GenerateurCodesSources.class, "Info.Help.Model"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Model.Print"));
			System.out.println("\t"
					+ MessageManager
							.get(GenerateurCodesSources.class, "Info.Help.Model.PrintBox"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Model.PrintModel"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Model.PrintElement"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Help.Model.Load"));
			System.out.println("\t"
					+ MessageManager
							.get(GenerateurCodesSources.class, "Info.Help.Model.Validate"));
			System.out.println("");
		}

		if (group.equals("pathGeneration") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.PathGeneration"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.PathGeneration.Print"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.PathGeneration.SetRoot"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.PathGeneration.Set"));
			System.out.println("");
		}

		if (group.equals("generate") || ObjectUtils.isNull(group)) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Help.Generate"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Generate.Generator"));
			System.out.println("\t"
					+ MessageManager
							.get(GenerateurCodesSources.class, "Info.Help.Generate.Model"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Generate.Element"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Generate.Mode"));
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class,
							"Info.Help.Generate.ContextBox"));
		}
	}

	public void info() throws ModelException {
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Help.Begin", new String[] { MessageManager.get(GenerateurCodesSources.class, "Info.GenerateurVersion") }));
		System.out.println("");
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Hello.OptionConfig", new String[]{ configFile }));
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Hello.OptionXSD", new String[]{ configXsdUrl }));
		System.out.println("");
		System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Info.AvailableModels"));
		System.out.println("\t - " + StringUtils.join(AvailableModels.getModels(), "\n\t - "));
	}

	/**
	 * Affiche le group courant
	 */
	public void groupPrint() {
		System.out.println(group);
	}

	/**
	 * Met à vide le group courant
	 */
	private void groupClear() {
		group = null;
	}

	/**
	 * Modifie le group courant
	 *
	 * @param value
	 *            La valeur dy groupe
	 */
	private void groupSet(String value) {
		group = value;
	}

	/**
	 * Relance la dernière commande
	 */
	private void traiterLast() {
		if (lastCommande != null) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.Execute", new String[] { lastCommande }));
			traiterCommandes(lastCommande);
		}
	}

	/**
	 * Affiche la dernière commande
	 */
	private void printLast() {
		if (lastCommande != null) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Last",
					new String[] { lastCommande }));
		}
	}

	/**
	 * Affiche le pool de commandes
	 */
	private void commandesPrint() {
		commandesPrint(null);
	}
	private void commandesPrint(String workspace) {
		if (ObjectUtils.isNull(workspace)) {
			for (String s : poolCommandes) {
				System.out.println(s);
			}
		} else {
			Workspace wk = workspaceManager.getWorkspace(workspace);
			if (ObjectUtils.isNotNull(wk)) {
				for (String s : wk.getPoolCommandes()) {
					System.out.println(s);
				}
			} else {
				System.err.println(MessageManager.get(GenerateurCodesSources.class, "Err.WorkspaceNotExist", new String[] { workspace }));
			}
		}
	}

	/**
	 * Affiche le pool de commandes
	 */
	private void commandesClear() {
		commandesClear(null);
	}
	private void commandesClear(String workspace) {
		if (ObjectUtils.isNull(workspace)) {
			poolCommandes = new LinkedList<String>();
		} else {
			Workspace wk = workspaceManager.getWorkspace(workspace);
			if (ObjectUtils.isNotNull(wk)) {
				wk.setPoolCommandes(new LinkedList<String>());
			} else {
				System.err.println(MessageManager.get(GenerateurCodesSources.class, "Err.WorkspaceNotExist", new String[] { workspace }));
			}
		}
	}

	private void commandesSave() {
		commandesSave(null);
	}
	private void commandesSave(String workspace) {
		if (ObjectUtils.isNull(workspace)) {
			saveGenerateur(new String[] { COMMANDES });
		} else {
			Workspace wk = workspaceManager.getWorkspace(workspace);
			if (ObjectUtils.isNotNull(wk)) {
				wk.save(new String[] { Workspace.PROP_COMMANDES });
			} else {
				System.err.println(MessageManager.get(GenerateurCodesSources.class, "Err.WorkspaceNotExist", new String[] { workspace }));
			}
		}
	}

	/**
	 * Affiche le pool de commandes
	 */
	private void commandesExecute() {
		commandesExecute(null);
	}
	private void commandesExecute(String workspace) {
		if (ObjectUtils.isNull(workspace)) {
			for (String commande : poolCommandes) {
				System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Execute", new String[] { commande }));
				traiterCommandes(commande);
			}
		} else {
			Workspace wk = workspaceManager.getWorkspace(workspace);
			if (ObjectUtils.isNotNull(wk)) {
				for (String commande : wk.getPoolCommandes()) {
					System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.Execute", new String[] { commande }));
					traiterCommandes(commande);
				}
			} else {
				System.err.println(MessageManager.get(GenerateurCodesSources.class, "Err.WorkspaceNotExist", new String[] { workspace }));
			}
		}
	}

	/**
	 * Affiche le pool de commandes
	 * <p>
	 * Attention, le add est présent en premier éléments du tableau
	 * </p>
	 */
	private void commandesAdd(String[] commandes) {
		if (ObjectUtils.isNull(commandes) || commandes.length < 2) {
			return;
		}

		if (commandes[1].equals("=")) {
			String commande = "";
			for (int i = 2; i < commandes.length; i++) {
				if (commande.equals("") == false) {
					commande += " ";
				}
				commande += commandes[i];
			}
			poolCommandes.add(commande);
		} else {
			Workspace wk = workspaceManager.getWorkspace(commandes[1]);
			if (ObjectUtils.isNotNull(wk)) {
				String commande = "";
				for (int i = 3; i < commandes.length; i++) {
					if (commande.equals("") == false) {
						commande += " ";
					}
					commande += commandes[i];
				}
				wk.addCommandToPool(commande);
			} else {
				System.err.println(MessageManager.get(GenerateurCodesSources.class, "Err.WorkspaceNotExist", new String[] { commandes[1] }));
			}
		}
	}

	/**
	 * Charge une archive de librairie dans un workspace
	 *
	 * @param workspace
	 *            le nom du workspace
	 * @param path
	 *            le dossier dans lequel créer le workspace
	 */
	public void workspaceCreate(String workspace, String path) {
		if (workspaceManager.containsWorkspace(workspace) == true) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.WorkspaceExist", new String[] { workspace }));
		} else {
			File dir = new File(path);
			if (dir.exists() == true) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.WorkspaceFileExist", new String[] { path }));
			} else {
				workspaceManager.addWorkspace(workspace, path);
				Workspace w = workspaceManager.getWorkspace(workspace);
				w.addObserver(observer);
				w.createWorkspace();
				if (workspaces.equals("") == false) {
					workspaces += ",";
				}
				workspaces += workspace + ";" + path;
			}
		}
	}

	/**
	 * Importe un workspace
	 *
	 * @param workspace
	 *            le nom du workspace
	 * @param path
	 *            le dossier dans lequel est le workspace
	 */
	private void workspaceImport(String workspace, String path) {
		if (workspaceManager.containsWorkspace(workspace) == true) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.WorkspaceExist", new String[] { workspace }));
		} else {
			File dir = new File(path);
			if (dir.exists() == true) {
				workspaceManager.addWorkspace(workspace, path);
				Workspace w = workspaceManager.getWorkspace(workspace);
				w.addObserver(observer);
				if (workspaces.equals("") == false) {
					workspaces += ",";
				}
				workspaces += workspace + ";" + path;
			} else {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.WorkspaceDirNotExist", new String[] { path }));
			}
		}
	}

	/**
	 * supprime un workspace
	 *
	 * @param workspace
	 *            le nom du workspace
	 * @param withContent
	 *            si on doit supprimer le répertoir aussi
	 */
	public void workspaceDelete(String workspace, boolean withContent) {
		if (workspaceManager.containsWorkspace(workspace) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.WorkspaceNotExist", new String[] { workspace }));
		} else {
			Workspace wk = workspaceManager.getWorkspace(workspace);
			if (withContent) {
				wk.removeWorkspace();
			}
			workspaceManager.removeWorkspace(workspace);

			String[] wkParts = workspaces.split(",");
			workspaces = "";
			for (String workspaceInfo : wkParts) {
				String[] workspaceParts = workspaceInfo.split(";");
				if (workspaceParts[0].equals(workspace) == false) {
					if (workspaces.equals("") == false) {
						workspaces += ",";
					}
					workspaces += workspaceInfo;
				}
			}

			if (wk.equals(currentWorkspace)) {
				currentWorkspace = null;
			}
		}
	}

	/**
	 * Affiche les workspaces
	 */
	private void workspacePrint() {
		StringBuilder builder = new StringBuilder();
		builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Workspaces"));

		if (workspaceManager.getWorkspacesList().size() == 0) {
			builder
					.append(" "
							+ MessageManager
									.get(GenerateurCodesSources.class, "Info.Nothing") + "\n");
		}

		System.out.println(builder.toString());

		for (Workspace w : workspaceManager.getWorkspacesList()) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Workspace",
							new String[] { w.getName(),
									w.getDirectory().getPath() }) + (w.equals(currentWorkspace) ? " (current)" : ""));
		}
	}

	/**
	 * Charge les éléments d'un workspace
	 *
	 * @param name
	 *            Le nom du workspace
	 */
	private void workspaceLoad(String name) {
		workspaceLoad(name, true);
	}
	private int workspaceLoad(String name, boolean execPreCommandes) {
		int res = GOOD_COMMANDE;
		currentWorkspace = null;
		try {
			if (workspaceManager.containsWorkspace(name) == false) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.WorkspaceNotExist", new String[] { name }));
			} else {
				Workspace w = workspaceManager.getWorkspace(name);
				w.addObserver(observer);
				w.load(manager);

				if (execPreCommandes) {
					res = execPreCommands(w.getPoolCommandes());
				}

				if (res == GOOD_COMMANDE) {
					currentWorkspace = w;
				}
			}
		} catch (LanguageException e) {
			e.printStackTrace(System.err);
			res = STOP_COMMANDE;
		} catch (SaxChargeurException e) {
			e.printStackTrace(System.err);
			res = STOP_COMMANDE;
		} catch (GeneratorException e) {
			e.printStackTrace(System.err);
			res = STOP_COMMANDE;
		} catch (GenerationException e) {
			e.printStackTrace(System.err);
			res = STOP_COMMANDE;
		}
		return res;
	}

	/**
	 * Affiche les langages disponibles
	 * <p>
	 * Affiche les langages présent dans la manager de languages.<b/> Affiche
	 * les langages présent dans la manager de générateurs.<b/>
	 * </p>
	 */
	private void languagePrint() {
		StringBuilder builder = new StringBuilder();

		builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Languages")
				+ " ");
		if (manager.getLanguagesManager().getAvailablesLanguages().length == 0) {
			builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Nothing"));
		}

		boolean first = true;
		for (String lg : manager.getLanguagesManager().getAvailablesLanguages()) {
			if (first == false) {
				builder.append(", ");
			}
			builder.append(lg);
			first = false;
		}

		builder.append("\n"
				+ MessageManager.get(GenerateurCodesSources.class, "Info.Generators") + " ");
		if (manager.getGeneratorsManager().getAvailablesLanguages().length == 0) {
			builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Nothing"));
		}

		first = true;
		for (String lg : manager.getGeneratorsManager()
				.getAvailablesLanguages()) {
			if (first == false) {
				builder.append(", ");
			}
			builder.append(lg);
			first = false;
		}

		System.out.println(builder.toString());
	}

	/**
	 * Affiche les éléments du language
	 *
	 * @param language
	 *            Le language à afficher
	 */
	private void languagePrint(String language) {
		if (manager.getLanguagesManager().containsLanguage(language) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.LanguageNotExist", new String[] { language }));
		} else {
			Language lg = manager.getLanguagesManager().getLanguage(language);

			System.out.println(lg.toString());
		}
	}

	/**
	 * Valide les langages
	 */
	private void languageValidate() {
		try {
			manager.getLanguagesManager().validate();
		} catch (ValidatorException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Affiche les librairie
	 */
	private void libraryPrint() {
		StringBuilder builder = new StringBuilder();
		builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Libraries"));

		if (manager.getGeneratorsManager().getNbLibraries() == 0) {
			builder
					.append(" "
							+ MessageManager
									.get(GenerateurCodesSources.class, "Info.Nothing") + "\n");
		}

		System.out.println(builder.toString());

		for (GeneratorsLibrary lib : manager.getGeneratorsManager().getLibraries().values()) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Library",
							new String[] { lib.getName(), lib.getLanguage() }));
			for (Dependency dependency : lib.getDependencies().values()) {
				System.out.println("\t\t"
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Dependency", new String[] { dependency.getName(), dependency.getUse().name() }));
			}
		}
	}

	/**
	 * Affiche les librairie du langage
	 *
	 * @param language
	 *            le language
	 */
	private void libraryPrintLanguage(String language) {
		if (manager.getGeneratorsManager().containsLanguage(language) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.LanguageNotExist", new String[] { language }));
		} else {
			List<GeneratorsLibrary> libraries = manager.getGeneratorsManager()
					.getLibraries(language);

			StringBuilder builder = new StringBuilder();
			builder.append(MessageManager.get(GenerateurCodesSources.class,
					"Info.LibrariesLanguage", new String[] { language }));

			if (libraries.size() == 0) {
				builder.append(" "
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Nothing")
						+ "\n");
			}

			System.out.println(builder.toString());

			for (GeneratorsLibrary lib : libraries) {
				System.out.println("\t"
						+ MessageManager.get(GenerateurCodesSources.class,
								"Info.LibraryLanguage", new String[] { lib
										.getName() }));
			}
		}
	}

	/**
	 * Affiche les générateurs du langage
	 *
	 * @param language
	 *            le language
	 */
	private void libraryPrintGenerators(String library) {
		if (manager.getGeneratorsManager().containsLibrary(library) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.LibraryNotExist", new String[] { library }));
		} else {
			GeneratorsLibrary lib = manager.getGeneratorsManager().getLibrary(
					library);

			StringBuilder builder = new StringBuilder();
			builder.append(MessageManager.get(GenerateurCodesSources.class,
					"Info.GeneratorsLibrary", new String[] { library }));

			if (lib.getNbGenerators() == 0) {
				builder.append(" "
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Nothing")
						+ "\n");
			}

			System.out.println(builder.toString());

			for (TemplateGenerator gen : lib.getGenerators().values()) {
				if (gen instanceof Generator) {
					Generator generator = (Generator) gen;
					System.out
							.println("\t"
									+ MessageManager
											.get(
													GenerateurCodesSources.class,
													"Info.GeneratorLibrary",
													new String[] {
															generator.getName(),
															generator
																	.getGeneratedObjectName() }));
					for (Property property : generator.getProperties().values()) {
						System.out.println("\t\t- "+property.toString());
					}
				}
			}

			builder = new StringBuilder();
			builder.append(MessageManager.get(GenerateurCodesSources.class,
					"Info.ApisLibrary", new String[] { library }));

			if (lib.getNbApis() == 0) {
				builder.append(" "
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Nothing")
						+ "\n");
			}

			System.out.println(builder.toString());

			for (Api gen : lib.getApis().values()) {
				System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.ApiLibrary",
								new String[] { gen.toString() }));
			}
		}
	}

	/**
	 * Charge une archive de librairie dans un workspace
	 *
	 * @param workspace
	 *            le workspace dans lequel chargé l'archive
	 * @param archive
	 *            l'archive à charger
	 */
	private void libraryLoad(String workspace, String archive) {
		try {
			if (workspaceManager.containsWorkspace(workspace) == false) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.WorkspaceNotExist", new String[] { workspace }));
			} else {
				workspaceManager.getWorkspace(workspace).addArchiveLibrary(
						manager, archive, false);
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		} catch (GeneratorException e) {
			e.printStackTrace(System.err);
		} catch (LanguageException e) {
			e.printStackTrace(System.err);
		} catch (SaxChargeurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Affiche les profiles
	 */
	private void profilePrint() {
		StringBuilder builder = new StringBuilder();
		builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Profiles"));

		if (manager.getXmiManager().getNbProfiles() == 0) {
			builder
					.append(" "
							+ MessageManager
									.get(GenerateurCodesSources.class, "Info.Nothing") + "\n");
		}

		System.out.println(builder.toString());

		for (Profile profile : manager.getXmiManager()
				.getProfiles().values()) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Profile",
							new String[] { profile.getName() }));
			for (Dependency dependency : profile.getDependencies().values()) {
				System.out.println("\t\t"
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Dependency", new String[] { dependency.getName(), dependency.getUse().name() }));
			}
		}
	}
	private void profilePrint(String profileName) {
		try {
			Profile profile = manager.getXmiManager().getProfile(profileName);
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Profile",
							new String[] { profile.getName() }));
			for (Dependency dependency : profile.getDependencies().values()) {
				System.out.println("\t\t"
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Dependency", new String[] { dependency.getName(), dependency.getUse().name() }));
			}

			System.out.println(profile.toString());
		} catch (ProfileNotFoundException e) {
			System.out.println(MessageManager.get(GenerateurCodesSources.class, "Info.ProfileNotExist", new String[] {profileName} ));
		}
	}

	/**
	 * Charge une archive de profile dans un workspace
	 *
	 * @param workspace
	 *            le workspace dans lequel chargé l'archive
	 * @param archive
	 *            l'archive à charger
	 */
	public void profileLoad(String workspace, String archive) {
		try {
			if (workspaceManager.containsWorkspace(workspace) == false) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.WorkspaceNotExist", new String[] { workspace }));
			} else {
				workspaceManager.getWorkspace(workspace).addArchiveProfile(
						manager, archive, false);
			}
		} catch (SaxChargeurException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} catch (GeneratorException e) {
			e.printStackTrace(System.err);
		} catch (LanguageException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Valide les langages
	 */
	private void profileValidate() {
		try {
			manager.getXmiManager().validate();
		} catch (ValidatorException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Affiche le chemin de la génération
	 */
	private void pathGenerationPrint() {
		System.out.println("root : "+manager.get(ContextProperty.PATH_GENERATION_ROOT));
		System.out.println("path : "+manager.get(ContextProperty.PATH_GENERATION));
	}

	/**
	 * Définit le chemin de génération
	 *
	 * @param path
	 *            Le chemin
	 */
	private void pathGenerationSetRoot(String path) {
		manager.set(ContextProperty.PATH_GENERATION_ROOT, path);
	}

	/**
	 * Définit le chemin de génération
	 *
	 * @param path
	 *            Le chemin
	 */
	private void pathGenerationSet(String path) {
		manager.set(ContextProperty.PATH_GENERATION, path);
	}

	/**
	 * Charge une boîte de modèles dans un workspace
	 *
	 * @param workspace
	 *            le workspace
	 * @param archive
	 *            l'archive à charger
	 */
	public void modelLoad(String workspace, String archive) {
		try {
			if (workspaceManager.containsWorkspace(workspace) == false) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.WorkspaceNotExist", new String[] { workspace }));
			} else {
				workspaceManager.getWorkspace(workspace).addArchiveModelsBox(
						manager, archive, false);
			}
		} catch (SaxChargeurException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} catch (GenerationException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Valide le super model d'une boîte
	 *
	 * @param name
	 *            Le nom de la boîte à valider
	 */
	private void modelValidate(String name) {
		modelValidate(name, null);
	}
	private void modelValidate(String name, String language) {
		try {
			if (manager.getModelsBoxesManager().containsBox(name) == false) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.BoxNotExist", new String[] { name }));
			} else {
				ModelsBox project = manager.getModelsBoxesManager()
						.getBox(name);
				project.addObserver(observer);
				if (project.loadSuperModel() == true) {
					project.getSuperModel().addObserver(observer);
					project.getSuperModel().validate(language);
				}
			}
		} catch (ValidatorException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Affiche les boîtes de modèles
	 */
	private void modelPrint() {

		StringBuilder builder = new StringBuilder();
		builder.append(MessageManager.get(GenerateurCodesSources.class, "Info.Models"));

		if (manager.getModelsBoxesManager().getNbBoxes() == 0) {
			builder
					.append(" "
							+ MessageManager
									.get(GenerateurCodesSources.class, "Info.Nothing") + "\n");
		}

		System.out.println(builder.toString());

		for (ModelsBox p : manager.getModelsBoxesManager().getBoxes().values()) {
			System.out.println("\t"
					+ MessageManager.get(GenerateurCodesSources.class, "Info.Model",
							new String[] { p.getName() }) + " (valide : " + String.valueOf(p.getSuperModel() == null ? false : p.getSuperModel().isValide()) + ")");
			for (com.imcfr.generateur.model.commun.Property property : p.getProperties().values()) {
				System.out.println("\t\t- "+property.toString());
			}
			for (Dependency dependency : p.getDependencies().values()) {
				System.out.println("\t\t"
						+ MessageManager.get(GenerateurCodesSources.class, "Info.Dependency", new String[] { dependency.getName(), dependency.getUse().name() }));
			}
		}
	}

	/**
	 * Affiche le contenu d'une boîte (supermodel)
	 *
	 * @param name
	 *            Le nom de la boîte
	 */
	private void modelPrint(String name, boolean detail) {
		if (manager.getModelsBoxesManager().containsBox(name) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.BoxNotExist", new String[] { name }));
		} else {
			ModelsBox box = manager.getModelsBoxesManager().getBox(name);
			if (box.getSuperModel() == null) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.BoxNotLoad", new String[] { box.getName() }));
			} else {
				if (detail == false) {
					for (GenericModel model : box.getSuperModel().getRealModels().values()) {
						System.out.println(model.getType()+":"+model.getName()+":"+model.getProject()+" ("+model.getVersion()+")");
					}
				} else {
					System.out.println(box.getSuperModel().toString());
				}
			}
		}
	}

	/**
	 * Affiche le contenu d'une boîte (supermodel) élément pas élément
	 *
	 * @param name
	 *            Le nom de la boîte
	 */
	private void modelPrintByElement(String name, boolean detail) {
		if (manager.getModelsBoxesManager().containsBox(name) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.BoxNotExist", new String[] { name }));
		} else {
			ModelsBox box = manager.getModelsBoxesManager().getBox(name);
			if (box.getSuperModel() == null) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.BoxNotLoad", new String[] { box.getName() }));
			} else {
				Scanner scanner = new Scanner(System.in);

				if (detail == false) {
					for (GenericModel model : box.getSuperModel().getRealModels().values()) {
						System.out.println(model.getType()+":"+model.getName()+":"+model.getProject()+" ("+model.getVersion()+")");
						System.out.println("\n"
								+ MessageManager.get(GenerateurCodesSources.class,
										"Info.Help.NextElement"));

						// on récup commande pour continuer sauf si q
						String commande = scanner.nextLine();
						if (commande != null && commande.equals("") == false
								&& commande.equals("q")) {
							break;
						}
					}
				} else {
					for (GenericModel model : box.getSuperModel().getModels()
							.values()) {

						for (GenericElement el : model.getElements().values()) {
							System.out.println(el.toString());
							System.out.println("\n"
									+ MessageManager.get(GenerateurCodesSources.class,
											"Info.Help.NextElement"));

							// on récup commande pour continuer sauf si q
							String commande = scanner.nextLine();
							if (commande != null && commande.equals("") == false
									&& commande.equals("q")) {
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Affiche le contenu d'un model d'une boîte
	 *
	 * @param boxName
	 *            Le nom de la boîte
	 * @param name
	 *            le nom du model
	 */
	private void projectPrintModel(String boxName, String name) {
		if (manager.getModelsBoxesManager().containsBox(boxName) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.BoxNotExist", new String[] { boxName }));
		} else {
			ModelsBox box = manager.getModelsBoxesManager().getBox(boxName);
			if (box.getSuperModel() == null) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.BoxNotLoad", new String[] { box.getName() }));
			} else {
				if (box.getSuperModel().containsModel(name) == true) {
					System.out.println(box.getSuperModel().getModel(name)
							.toString());
				} else {
					System.err.println(MessageManager
							.get(GenerateurCodesSources.class, "Err.ModelNotExist",
									new String[] { name, boxName }));
				}
			}
		}
	}

	/**
	 * Affiche le contenu d'un model d'une boîte élément par élément
	 *
	 * @param boxName
	 *            Le nom de la boîte
	 * @param name
	 *            le nom du model
	 */
	private void projectPrintModelByElement(String boxName, String name) {
		if (manager.getModelsBoxesManager().containsBox(boxName) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.BoxNotExist", new String[] { boxName }));
		} else {
			ModelsBox box = manager.getModelsBoxesManager().getBox(boxName);
			if (box.getSuperModel() == null) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.BoxNotLoad", new String[] { box.getName() }));
			} else {
				Scanner scanner = new Scanner(System.in);

				if (box.getSuperModel().containsModel(name) == true) {
					GenericModel model = box.getSuperModel().getModel(name);

					for (GenericElement el : model.getElements().values()) {
						System.out.println(el.toString());
						System.out.println("\n"
								+ MessageManager.get(GenerateurCodesSources.class,
										"Info.Help.NextElement"));

						// on récup commande pour continuer sauf si q
						String commande = scanner.nextLine();
						if (commande != null && commande.equals("") == false
								&& commande.equals("q")) {
							break;
						}
					}
				} else {
					System.err.println(MessageManager
							.get(GenerateurCodesSources.class, "Err.ModelNotExist",
									new String[] { name, boxName }));
				}
			}
		}
	}

	/**
	 * Affiche le contenu d'un élément d'une boîte
	 *
	 * @param boxName
	 *            Le nom de la boîte
	 * @param element
	 *            l'id de l'élément
	 */
	private void modelPrintElement(String boxName, String element) {
		if (manager.getModelsBoxesManager().containsBox(boxName) == false) {
			System.err.println(MessageManager.get(GenerateurCodesSources.class,
					"Err.BoxNotExist", new String[] { boxName }));
		} else {
			ModelsBox box = manager.getModelsBoxesManager().getBox(boxName);
			if (box.getSuperModel() == null) {
				System.err.println(MessageManager.get(GenerateurCodesSources.class,
						"Err.BoxNotLoad", new String[] { box.getName() }));
			} else {
				if (box.getSuperModel().containsElement(element) == true) {
					System.out.println(box.getSuperModel().getElement(element)
							.toString());
				} else {
					System.err.println(MessageManager.get(GenerateurCodesSources.class,
							"Err.ElementNotExist", new String[] { element }));
				}
			}
		}
	}

	/**
	 * Lancement de la génération
	 *
	 * @param generator
	 *            le générateur
	 * @param model
	 *            le model
	 * @param element
	 *            l'élément à générer
	 * @param mode les fonctions à éxécuter
	 */
	public void generate(String generator, String model, String element, Support mode, String contextBox) {
		try {
			manager.generate(generator, model, element, mode, contextBox);

			System.out.println(MessageManager.get(GenerateurCodesSources.class,
					"Info.EndGeneration"));
		} catch (LanguageNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (GenerationException e) {
			e.printStackTrace(System.err);
		} catch (GeneratorException e) {
			e.printStackTrace(System.err);
		}
	}
}
