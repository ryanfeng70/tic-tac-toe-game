import java.util.*;
import java.io.*;


public class tictactoe{
    
    public static String[][] makeboard() {
        String[][] array = new String[3][3];
        
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                array[i][j]= " ";
            }
        }      
        return array;
    }
    
    public static void printboard(String[][] array) {
        System.out.println("   Current Board           0       1       2     Key");
        System.out.println("  -----------------      -----------------------");
        System.out.printf("  | %s | | %s | | %s |    0 | 0,0 | | 0,1 | | 0,2 |", array[0][0], array[0][1], array[0][2]);
        System.out.println("");
        System.out.println("  -----------------      -----------------------");
        System.out.printf("  | %s | | %s | | %s |    1 | 1,0 | | 1,1 | | 1,2 |", array[1][0], array[1][1], array[1][2]);
        System.out.println("");
        System.out.println("  -----------------      -----------------------");
        System.out.printf("  | %s | | %s | | %s |    2 | 2,0 | | 2,1 | | 2,2 |", array[2][0], array[2][1], array[2][2]);
        System.out.println("");
        System.out.println("  -----------------      -----------------------");
    }
    
    public static void getusermove(String[][] array) throws Exception{
        Scanner scnr = new Scanner(System.in);
        boolean validmove = false;
        System.out.println("________________________________________________________");
        System.out.println("YOUR TURN");
        
        while (validmove==false) {
            System.out.println("Enter the row number: ");
            int firstnum = scnr.nextInt();
            System.out.println("Enter the column number: ");
            int secondnum = scnr.nextInt();
        
            if(!array[firstnum][secondnum].equals(" ")) {
                System.out.println("Spot is already occupied. Go again.");
            }
                else {
                    array[firstnum][secondnum]="X";
                    validmove = true;
                }
            }
    }
                    
    public static void getcomputermove(String[][] array) throws Exception{
        Random rand = new Random();
        boolean oneemptyspace = false;
        boolean mademove = false;
        int i ;
        int j ;
        System.out.println("________________________________________________________");
        System.out.println("COMPUTER'S TURN");
        
        for(i=0; i<3; i++) {
            for(j=0; j<3; j++) {
                if(" ".equals(array[i][j])) {
                    oneemptyspace = true; /*checks for at least 1 empty space*/
                }
            }
        }
        
        if(oneemptyspace==true) { /*if there's 1 empty space, then start generating random placements*/
            while(mademove==false) { /*Keeps generating random placements until it picks an open spot*/
                int firstnum = rand.nextInt((2 - 0) + 1) + 0;
                int secondnum = rand.nextInt((2 - 0) + 1) + 0;
                
                if(" ".equals(array[firstnum][secondnum])) {
                    array[firstnum][secondnum]="O";
                    mademove=true;
                    System.out.println("Computer has made a turn at "+firstnum+","+secondnum);
                }
            }
        }
        else {
            throw new Exception("No open spaces. Computer could not make a turn.");
        }
    
    } 
    
    public static boolean draw(String array[][]) {
        boolean oneemptyspace = false;
        
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(" ".equals(array[i][j])) {
                    oneemptyspace = true; /*checks for at least 1 empty space*/
                }
            }
        }
        return oneemptyspace;
    }

    public static boolean checkwon(String array[][]) {
        boolean win = false;
        
        for(int i=0; i<3; i++) {
            if(array[i][0].equals(array[i][1]) && !array[i][1].equals(" ") && array[i][1].equals(array[i][2])) {
                win = true; /*checks if whole row is the same and not all nulls*/
            }
            else if(array[0][i].equals(array[1][i]) && !array[1][i].equals(" ") && array[1][i].equals(array[2][i])) {
                win = true; /*checks if whole column is the same and not all nulls*/
            }
        }
        if(array[0][0].equals(array[1][1]) && !array[1][1].equals(" ") && array[1][1].equals(array[2][2])) {
            win = true; /*checks if whole diagonal is the same and not all nulls*/
            }
        else if(array[2][0].equals(array[1][1]) && !array[1][1].equals(" ") && array[1][1].equals(array[0][2])) {
            win = true; /*checks if whole diagonal is the same and not all nulls*/
        }  
        return win;
    }
    
    public static String checkwonwhat(String array[][]) {
        String win = "";
        
        for(int i=0; i<3; i++) {
            if(array[i][0].equals(array[i][1]) && !array[i][1].equals(" ") && array[i][1].equals(array[i][2])) {
                win = "full row"; /*checks if whole row is the same and not all nulls*/
            }
            else if(array[0][i].equals(array[1][i]) && !array[1][i].equals(" ") && array[1][i].equals(array[2][i])) {
                win = "full column"; /*checks if whole column is the same and not all nulls*/
            }
        }
        if(array[0][0].equals(array[1][1]) && !array[1][1].equals(" ") && array[1][1].equals(array[2][2])) {
            win = "full diagonal (top left to bottom right)"; /*checks if whole diagonal is the same and not all nulls*/
            }
        else if(array[2][0].equals(array[1][1]) && !array[1][1].equals(" ") && array[1][1].equals(array[0][2])) {
            win = "full diagonal (bottom left to top right)"; /*checks if whole diagonal is the same and not all nulls*/
        }
        
        return win;
    }
   
    public static void writeToFile(String win, String fileName) throws IOException{
        File file = new File(fileName);
        int count = 1;
        if(file.exists()){
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            
            while (lnr.readLine()!=null){
                count++;
            }
        }
        
        try{
            FileWriter fw  = new FileWriter(fileName, true);
            fw.write("Game " + count + ": " + win + "\n");
            fw.close();
        }
        catch (Exception excp){
            excp.printStackTrace();
        }
    }
    
    public static void readFromFile(String fileName){
        try{
            Scanner scnr = new Scanner(new File(fileName));
            while (scnr.hasNextLine()){
                System.out.println(scnr.nextLine());
            }
            
        }
        catch(IOException excp){
            System.out.println(excp.getMessage());
        }

    }
    
    public static void main(String[] args) throws IOException {
        readFromFile("games.txt");
        String[][] array = makeboard();
        String winner = "";
        printboard(array);
        
        while(checkwon(array)==false) {
            try{
            getusermove(array);
            }
            catch (Exception excp){
                System.out.println(excp.getMessage());
            }
            printboard(array);
            if(checkwon(array)==true) {
                winner = "Human player wins";
                break;
            }
            if(draw(array)==false) {
                winner = "It's a Draw";
                break;
            }
            try{
            getcomputermove(array);
            }
            catch (Exception excp){
                System.out.println(excp.getMessage());
            }
            printboard(array);
            if(checkwon(array)==true) {
                winner = "Computer wins";
                break;
            }
            if(draw(array)==false) {
                winner = "It's a Draw";
                break;
            }
        }
        
        System.out.println("________________________________________________________");
        System.out.println(checkwonwhat(array));
        
        writeToFile(winner, "games.txt");
        readFromFile("games.txt");
        
        
        
    }
    
}