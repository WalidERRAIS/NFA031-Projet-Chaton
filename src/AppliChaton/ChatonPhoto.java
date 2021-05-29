package AppliChaton;

public class ChatonPhoto {
	
	//méthode affichage Menu
	public static void afficherMenu () {
		Terminal.ecrireStringln ("Voici les opérations disponibles : ");
		Terminal.sautDeLigne();
		Terminal.ecrireStringln ("1: afficher liste des chatons,");
		Terminal.ecrireStringln ("2: afficher l'image et la note d'un chaton,");
		Terminal.ecrireStringln ("3: ajouter une note pour un chaton,");
		Terminal.ecrireStringln ("4: afficher le classement des chatons,"); 
		Terminal.ecrireStringln ("5: retirer un ou plusieurs chatons,"); 
		Terminal.ecrireStringln ("6: ajouter un ou plusieurs chatons,");
		Terminal.ecrireStringln ("7: quitter le programme.");
		Terminal.sautDeLigne();
		Terminal.ecrireString("Votre choix : ");
	}
	//méthode 1 afficher liste chatons
	public static void afficherListeChaton (String [] tabChaton) {
		Terminal.ecrireStringln("--------------------------");
		System.out.println("Liste des chatons : ");
		Terminal.sautDeLigne();
		//affichage tableau de chatons
		for (int i=0; i<tabChaton.length; i++) {
			if (tabChaton[i]!=null) {
				System.out.println((i+1)+" "+tabChaton[i]);
			}
		}
		Terminal.ecrireStringln("--------------------------");
	}
	//méthode 2 afficher l'image et la note d'un chaton
	public static void afficherNoteChaton (double [] [] tabnotes, int indiceChaton, String [] tabChaton,int nbVotes) {				
		if (tabChaton[indiceChaton]!=null) {
			double moy =0;
			//Calcul moyenne des notes pour chaton saisit
			for (int j=0; j<tabnotes[indiceChaton].length; j++) {
				//incrémente nbr de votes chaque fois qu'une case à une note
				if (tabnotes[indiceChaton][j]!=0) {
					nbVotes++;
					moy = tabnotes[indiceChaton][j] + moy;
				}
			}
			//attention division par zéro!
			if (nbVotes!=0) {
				moy = moy/nbVotes;
			}
			//affichage note
			System.out.println((indiceChaton+1)+"- "+tabChaton[indiceChaton]+"    "+moy+" ("+nbVotes+" votes)");
		}
			
	}	
	//méthode 3 ajouter note chaton
	public static double[][] ajouterNoteChaton (double [] [] tabnotes, int indiceChaton, double noteChaton, int j, int tailleTabnotes) {	
		//vérifie case vide et ajoute note utilisateur
		if (j<=tailleTabnotes) {
			if (tabnotes[indiceChaton][j]==0) {
				tabnotes[indiceChaton][j]= noteChaton;
			}
		//incrémente case et rappel la méthode ajout de note
			else {
				j++;
				ajouterNoteChaton(tabnotes, indiceChaton, noteChaton, j, tailleTabnotes);
			}
		}
		return tabnotes;
	}
	//méthode 4 afficher classement
	public static void afficherClassementChaton (double [] [] tabnotes, double [] moyennes, int [] nbVotes, String [] tabChatonTrie, int [] numChaton) {
		Terminal.ecrireStringln("--------------------------");
		System.out.println("Classement des chatons selon leurs notes : ");
		Terminal.sautDeLigne();
		calculMoyennes(moyennes, tabnotes, nbVotes);
		triTableau(moyennes, nbVotes, tabChatonTrie, numChaton);
		for (int i=0; i<tabChatonTrie.length; i++) {
			if (tabChatonTrie[i]!=null) {
				System.out.print(numChaton[i]+"-  ");
				System.out.print(tabChatonTrie[i]+"  ");
				System.out.print(moyennes[i]+"  ");
				System.out.print("("+nbVotes[i]+" votes)");
				System.out.println();
			}
		}
	}
	//Initialisation tableau chaton trié
	public static String [] initialisationTabChatonTrie (String [] tabChaton, String [] tabChatonTrie) {
		for (int i=0; i<tabChaton.length; i++) {
			if (tabChaton[i]!=null) {
				tabChatonTrie[i]=tabChaton[i];
			}
		}
		return tabChatonTrie;
	}
	public static int [] initialisationTabNumChaton (String [] tabChaton, int [] numChaton) {
	//Initialisation tableau numéro correspondant à chaque chaton
		for (int i=0; i<tabChaton.length; i++) {
			if (tabChaton[i]!= null) {
				numChaton[i]= i+1;
			}
		}
		return numChaton;
	}
	//méthode calcul et initialisation tableau moyennes
	public static double [] calculMoyennes (double [] moyennes, double [] [] tabnotes, int [] nbVotes) {
		int i=0;
		for (; i<tabnotes.length; i++) {
			for (int j=0; j<tabnotes[i].length; j++) {
				//incrémente nbr de votes chaque fois qu'une case à une note
				if (tabnotes[i][j]!=0) {
					nbVotes[i]++;
					moyennes [i] = tabnotes[i][j] + moyennes[i];
				}
			}
			//attention division par zéro!
			if (nbVotes[i]!=0) {
				moyennes[i] = moyennes[i]/nbVotes[i];
			}
		}
		return moyennes;
	}
	//méthode tri décroissant des tableaux 
	public static double [] triTableau (double [] moyennes, int [] nbVotes, String [] tabChatonTrie, int [] numChaton) {
		double tmp;
		String posChaton;
		int num;
		int nb;
		for (int k=0; k<tabChatonTrie.length; k++) {
			if (tabChatonTrie!= null) {
				//parcours tableau de la fin à 0
				for (int i=moyennes.length-1; i>=0; i--) { 
					//parcours tableau du début pour mettre valeur min à la fin 
					for (int j=0; j<i ;j++) {
						if (moyennes[j]<moyennes[j+1]) {
							//échange valeur j avec valeur j+1 si j inférieur
							tmp=moyennes[j];
							moyennes[j] = moyennes[j+1];
							moyennes[j+1]=tmp;
							//échange valeur nom chaton
							posChaton= tabChatonTrie[j];
							tabChatonTrie[j] = tabChatonTrie[j+1];
							tabChatonTrie[j+1] = posChaton;
							//échange valeur num chaton
							num = numChaton[j];
							numChaton[j]= numChaton[j+1];
							numChaton[j+1]= num;
							//échange valeur nbr votes
							nb= nbVotes[j];
							nbVotes[j]=nbVotes[j+1];
							nbVotes[j+1]= nb;
						}
					}
				}
			}
		}
		return moyennes;
	}
	//méthode calcul nb de chaton dans liste
	public static int calculNbChaton (String [] tabChaton, int nbChatonListe) {
		//calcul nbr de chaton dans la liste
		for (int i=0; i<tabChaton.length; i++) {
			if (tabChaton[i]!=null) {
				nbChatonListe++;
			}
		}
		return nbChatonListe;
	}
	//méthode 5 retirer un ou plusieurs chaton
	public static String [] retirerChatonListe (String [] tabChaton, String [] photoChaton,int indiceChaton, int [] numChaton, String [] tabChatonTrie) {
		//vérifie pas moins de 5 chatons
		tabChaton[indiceChaton] = null;
		photoChaton[indiceChaton] = null;
		for (int i=0; i<numChaton.length; i++) {
			if (numChaton[i]==indiceChaton+1) {
				tabChatonTrie[i]= null;
				Terminal.sautDeLigne();
				System.out.println("Le chaton a bien été retiré de la liste.");
			}
		}
		return tabChaton;
	}
	//méthode tableau nom intermediaire pour fichier photo
	public static String [] ChoisirPhotoChatonAjoute ( String [] tabNomIntermediaire, String [] tabPhotoChoisir) {
		//Initialisation tableau nom intermediaire
		for (int i=0; i<tabNomIntermediaire.length; i++) {
			tabNomIntermediaire[i]= "photoNum"+(i+1);
		}
		//Initialisation tableau photo à choisir pour chaton ajouté
		for (int i=0; i<tabPhotoChoisir.length; i++) {
			tabPhotoChoisir[i]= tabNomIntermediaire[i]+".jpg";
		}
		return tabPhotoChoisir;
	}
	//méthode 6 ajouter un ou plusieurs chaton
	public static String [] ajouterChatonListe (String [] tabChaton, int nbChatonListe, String nomChatonAjoute) {
		//cherche une case vide pour ajouter chaton
		int i=0;
		for (; i<tabChaton.length; i++) {
			if (tabChaton[i]==null) {
				tabChaton[i]=nomChatonAjoute;
				Terminal.sautDeLigne();
				System.out.println("Le chaton a bien été ajouté à la liste.");
				break;
			}
		}
		return tabChaton;
	}
	//méthode 7	défile les photos de l'album et choisit photo à attribuer au chaton ajouté
	public static String [] defilerPhotoChoisir (String[] tabPhotoChoisir,String [] photoChaton,int saisitNumPhoto, int indiceChatonAjoute) {
		//affichage des images une à une 
		System.out.println("Veuillez choisir parmi notre album photo une image à attribuer à votre chaton");
		Terminal.ecrireStringln("--------------------------");
		for (int j=0; j<tabPhotoChoisir.length; j++) {
			//afficher l'image seulement pour les cases avec une adresse
			if (tabPhotoChoisir[j]!=null) {
				AfficheImage.afficheImage(tabPhotoChoisir[j]);
				System.out.println("Voici la photo numéro "+(j+1)+" de notre album");
				Terminal.sautDeLigne();
				System.out.println("Tapez <entrée> pour voir photo suivante");
				Terminal.lireString();
				AfficheImage.fermeImage();
				Terminal.ecrireStringln("--------------------------");
			}
		}
		System.out.println("Donnez le numéro de l'image que vous voulez attribuer au chaton ajouté : ");
		//appel méthode lire numéro photo chaton ajouté
		saisitNumPhoto= lireNumeroPhotoChatonAjoute (tabPhotoChoisir,"Donnez le numéro de l'image que vous voulez attribuer au chaton ajouté : ");
		photoChaton[indiceChatonAjoute]= tabPhotoChoisir[saisitNumPhoto];
		tabPhotoChoisir[saisitNumPhoto]=null;
		Terminal.ecrireStringln("--------------------------");
		return photoChaton;
	}
	//méthode lire numéro photo chaton ajouté
	public static int lireNumeroPhotoChatonAjoute (String [] tabPhotoChoisir, String message) {
		int saisit= lireInt()-1;
		while ((saisit<0) || (saisit>tabPhotoChoisir.length)) {
			System.out.println("Incorrect.");
			System.out.println(message);
			saisit= lireInt()-1;
		}
		return saisit;
	}
	//méthode lire entier au clavier
	public static int lireInt () {
		while (true) {
			//si different de entier retourne erreur
			try {
				return (Terminal.lireInt());	
			}
			catch (TerminalException ex) {
				System.out.println("Incorrect. Entrez un nombre.");
				Terminal.sautDeLigne();
				System.out.println("Votre choix : ");
			}
		}
	}	
	//méthode retourne choix utilisateur compris entre 1 et 7
	public static int lireChoixUtilisateur (String message) {
		int choixUtilisateur = lireInt();
		while ((choixUtilisateur<1) || (choixUtilisateur>7)) {
			System.out.println("Incorrect. Entrez un nombre compris entre 1 et 7.");
			Terminal.sautDeLigne();
			System.out.println(message);
			choixUtilisateur = lireInt();
		}
		return choixUtilisateur;
	}
	//méthode retourne numero du chaton choisit
	public static int lireNumeroChaton (String [] tabChaton, String message) {
		int indiceChaton= lireInt()-1;
		while ((indiceChaton<0) || (indiceChaton> tabChaton.length-1)) {
			System.out.println("Incorrect. Saisissez un numéro correspondant à un chaton.");
			Terminal.sautDeLigne();
			System.out.println(message);
			indiceChaton= lireInt()-1;			
		}	
		while (tabChaton[indiceChaton]==null) {
			System.out.println("Le numéro saisit ne correspond à aucun chaton. Recommencez.");
			Terminal.sautDeLigne();
			System.out.println(message);
			indiceChaton= lireInt()-1;
		}
		return indiceChaton;
	}
	//méthode lire double clavier 
	public static double lireDouble () {
		while (true) {
			//si different de nombre retourne erreur
			try {
				return (Terminal.lireDouble());	
			}
			catch (TerminalException ex) {
				System.out.println("Incorrect. Entrez un nombre : ");
			}
		}
	}
	//méthode retourne note compris entre 1 et 5
	public static double lireNoteChaton () {
		double note = lireDouble();
		while ((note<1) || (note>5)) {
			System.out.println("Incorrect. Entrez un nombre compris entre 1 et 5 : ");
			note = lireDouble();
		}
		return note;
	}
	public static void main(String[] args) {
		//nombre de votes possible par chaton
		int tailleTabnotes = 30;
		String [] tabChaton = new String [10];
		double [] [] tabnotes = new double [tabChaton.length] [tailleTabnotes];
		String [] photoChaton = new String [tabChaton.length];
		//Initialisation tableau chatons
		tabChaton [0] = "Shanks";
		tabChaton [1] = "Kassel";
		tabChaton [2] = "Mirabelle";
		tabChaton [3] = "Canelle";
		tabChaton [4] = "Grominet";
		tabChaton [5] = "Miaous";
		tabChaton [6] = "Platon";
		//Initialisation tableau photo des chatons en jpg 
		for (int i=0; i<tabChaton.length; i++) {
			if (tabChaton[i]!=null) {
				photoChaton[i]= tabChaton[i]+".jpg";
			}
		}
		//Appel méthode initialisation tableau image à choisir pour chaton ajouté
		//attention il faut autant de photos de chaton dans le dossier que la taille du tableau
		int tailleTabPhotoChoisir = 5;
		String [] tabNomIntermediaire = new String [tailleTabPhotoChoisir]; 
		String [] tabPhotoChoisir = new String [tailleTabPhotoChoisir];
		ChoisirPhotoChatonAjoute(tabNomIntermediaire,tabPhotoChoisir);		
		//Affichage Menu des opérations
		Terminal.ecrireStringln ("Bienvenue sur l’application de CatTV.");
		Terminal.ecrireStringln ("Comme chaque année, nous organisons une élection pour élire le chaton le plus mignon.");
		Terminal.sautDeLigne();
		afficherMenu();
		//lecture choix Utilisateur
		int choix = lireChoixUtilisateur ("Votre choix : ");
		while ((choix>=1) && (choix<=7)) {
			//choix 1 liste chaton
			if (choix == 1) {
			//appel méthode 1 afficher liste chaton
				afficherListeChaton(tabChaton);
			}
			//choix 2 afficher image et note chaton 
			else if (choix == 2) {
				afficherListeChaton(tabChaton);
				//choix chaton
				System.out.print("Afficher l'image et la note du chat numéro : ");
				int nbVotes=0;
				//appel méthode lire numéro chaton 
				int indiceChaton= lireNumeroChaton (tabChaton, "Votre choix : ");
				//appel méthode 2 afficher image et note du chaton
				afficherNoteChaton(tabnotes, indiceChaton,tabChaton,nbVotes);
				//Affichage photo chaton choisit
				AfficheImage.afficheImage(photoChaton[indiceChaton]);
				Terminal.sautDeLigne();
				System.out.println("Tapez <entrée> pour continuer");
				Terminal.lireString();
				AfficheImage.fermeImage();
				Terminal.ecrireStringln("--------------------------");	
			}	
			//choix 3 ajouter note chaton
			else if (choix == 3) {
				afficherListeChaton(tabChaton);
				//Choix chaton et note attribué
				System.out.print("Donner une note au chat numéro : ");
				//appel méthode lire numéro chaton 
				int indiceChaton= lireNumeroChaton (tabChaton, "Votre choix : ");
				AfficheImage.afficheImage(photoChaton[indiceChaton]);				
				System.out.print("Entrez le nombre d'étoiles (1 à 5) : ");
				//appel méthode lire note clavier
				double noteChaton = lireNoteChaton();
				AfficheImage.fermeImage();
				//appel méthode 3 ajouter note chaton
				int j=0;
				ajouterNoteChaton(tabnotes,indiceChaton,noteChaton, j, tailleTabnotes);
				Terminal.ecrireStringln("--------------------------");
			}
			//choix 4 afficher classement selon note
			else if (choix == 4) {
				double [] moyennes= new double [tabChaton.length];
				int [] nbVotes= new int [tabChaton.length];
				String [] tabChatonTrie = new String [tabChaton.length]; 
				int [] numChaton= new int [tabChaton.length];
				//appel méthode initialisation tableau chaton trié
				initialisationTabChatonTrie (tabChaton, tabChatonTrie);
				//appel méthode initialisation tableau numéro correspondant à chaque chaton
				initialisationTabNumChaton (tabChaton, numChaton);
				//appel méthode 4 afficher classement
				afficherClassementChaton(tabnotes,  moyennes,  nbVotes,  tabChatonTrie, numChaton);
				Terminal.ecrireStringln("--------------------------");
			}
			//choix 5 retirer un ou plusieurs chatons
			else if (choix == 5) {
				double [] moyennes= new double [tabChaton.length];
				int [] nbVotes= new int [tabChaton.length];
				String [] tabChatonTrie = new String [tabChaton.length]; 
				int [] numChaton= new int [tabChaton.length];
				//appel méthode initialisation tableau chaton trié
				initialisationTabChatonTrie (tabChaton, tabChatonTrie);
				//appel méthode initialisation tableau numéro correspondant à chaque chaton
				initialisationTabNumChaton (tabChaton, numChaton);
				//appel méthode 4 afficher classement
				afficherClassementChaton(tabnotes,  moyennes,  nbVotes,tabChatonTrie, numChaton);
				Terminal.sautDeLigne();
				int nbChatonListe=0;
				//calcul et vérifie nb de chaton supérieur à 5 
				int nb = calculNbChaton(tabChaton,nbChatonListe);
				if (nb>5) {
					System.out.print("Entrez le numéro du chaton que vous souhaitez retirer de la liste : ");
					int indiceChaton= lireNumeroChaton (tabChaton, "Votre choix : ");
					//appel méthode 5 retirer un ou plusieurs chaton
					retirerChatonListe(tabChaton, photoChaton, indiceChaton, numChaton, tabChatonTrie);
					//affiche liste chaton
					afficherListeChaton(tabChaton);
				}
				else {
					System.out.println("Le nombre de chaton doit être compris entre 5 et 10.");
					Terminal.ecrireStringln("--------------------------");
				}
			}	
			//choix 6 ajouter un ou plusieurs chaton
			else if (choix == 6) {
				boolean saisieNom=false;
				int nbChatonListe=0;
				//calcul et vérifie nb de chaton inférieur à 10
				int nb = calculNbChaton(tabChaton,nbChatonListe);
				if (nb<10) {						
					while (saisieNom==false) {
						System.out.println("Quel est le nom du chaton que vous souhaitez ajouter : ");
						String nomChatonAjoute= Terminal.lireString();
						//vérifie nom du chaton possédent au moins 1 caractère et moins de 25 caractères
						if ((nomChatonAjoute.length()>0) && (nomChatonAjoute.length()<25)) {
							saisieNom=true;
							//appel méthode 6 ajouter un ou plusieurs chatons
							ajouterChatonListe(tabChaton, nbChatonListe, nomChatonAjoute);
							//affiche liste chaton
							afficherListeChaton(tabChaton);
							//choix chaton auquel attribuer une image
							int saisitNumPhoto=0;
							boolean saisieChoix7=false;
							while (saisieChoix7==false) {
								System.out.println("Saisissez le numéro du chaton pour lequel vous souhaitez attribuer une image : ");
								int indiceChatonAjoute = lireNumeroChaton (tabChaton, "Votre choix : ");
								if ((photoChaton[indiceChatonAjoute]==null) && (tabChaton[indiceChatonAjoute]!= null)) {
									defilerPhotoChoisir(tabPhotoChoisir, photoChaton, saisitNumPhoto, indiceChatonAjoute);
									saisieChoix7=true;
								}
								else {
									System.out.println("Le chat que vous avez sélectionné possédent déjà une image.");
									Terminal.sautDeLigne();
								}
							}
						}
						else {
							System.out.println("Saisi incorrect. Recommencez.");
							Terminal.ecrireStringln("--------------------------");
						}
					}
				}
				else {
					System.out.println("Le nombre de chaton doit être compris entre 5 et 10.");
					Terminal.ecrireStringln("--------------------------");
				}
			}					
			//choix 7 quitter programme
			else if (choix == 7) {
				System.out.print("Merci pour votre participation. A bientôt !");
				break;
			}
			//appel Menu des opérations
			afficherMenu();
			//lecture choix utilisateur
			choix = lireChoixUtilisateur ("Votre choix : ");			
		}
	}
}			


