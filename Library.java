package game.languagelearning;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import game.languagelearning.memento.LevelMemento;
import game.languagelearning.singleton.SingletonPoint;

public class Library {
    private static Library _instance;
    private int numOfWords = 10;
    private int level = 1;
    private int nativeLng;
    private boolean[] checkedLng;
    private ArrayList<String> library;
    private String[] levelWord;
    private String[]  wordToRemove;
    private LevelMemento mem;
    private ArrayList<String> saves;
    //constructor
    private Library(){
        this.checkedLng = new boolean[5];
        this.levelWord=new String[5];
        this.wordToRemove = new String[numOfWords];
        loadWordsToLibrary();
        this.mem = new LevelMemento();
        this.saves = new ArrayList<>();
//      loadLibrary();
    }

    //singleton instance
    public synchronized static Library getInstance() {
        if (_instance	== null	)   _instance	= 	new Library();
        return _instance;
    }

    //setters and getters
    public int getNumOfWords() {
        return numOfWords;
    }
    public void setNumOfWords(int numOfWords) {
        this.numOfWords = numOfWords;
    }
    public int getLevel() {
        return level;
    }
    public int setLevel(int level) {
        this.level = level;
        return level;
    }
    public void nextLevel() {
        ++this.level;
    }
    public int getNativeLng(){
        return nativeLng;
    }
    public int setNativeLng(int i){
        if(i>=0 && i<5)
            this.nativeLng = i;
        return nativeLng;
    }
    public boolean[] getCheckedLng(){
        return this.checkedLng;
    }
    public void setCheckedLng(boolean[] checkedLng){
        this.checkedLng=checkedLng;
    }
    public void changeCheckedLng(int i, boolean isChecked){
        if(i>=0 && i<5) this.checkedLng[i] = isChecked;
    }
    //Library
    public ArrayList<String> getLibrary() {
        return library;
    }
    public void setLibrary(ArrayList<String> library) {
        this.library = library;
    }
    private void loadWordsToLibrary(){
        String words =
                "Second\n" +
                        "сЭканд\n" +
                        "שניה\n" +
                        "шния\n" +
                        "Секунда\n" +
                        "Minute\n" +
                        "мИнат\n" +
                        "דקה\n" +
                        "дакА\n" +
                        "Минута\n" +
                        "Hour\n" +
                        "Ауэр\n" +
                        "שעה\n" +
                        "шаА\n" +
                        "Час\n" +
                        "Day\n" +
                        "дЭй\n" +
                        "יום\n" +
                        "Ём\n" +
                        "День\n" +
                        "Week\n" +
                        "уИк\n" +
                        "שבוע\n" +
                        "шавУа\n" +
                        "Неделя\n" +
                        "Month\n" +
                        "мАнф\n" +
                        "חודש\n" +
                        "хОдэш\n" +
                        "Месяц\n" +
                        "Year\n" +
                        "Еэр\n" +
                        "שנה\n" +
                        "шанА\n" +
                        "Год\n" +
                        "I\n" +
                        "Ай\n" +
                        "אני\n" +
                        "анИ\n" +
                        "Я\n" +
                        "We\n" +
                        "уИ\n" +
                        "אנחנו\n" +
                        "анАхну\n" +
                        "Мы\n" +
                        "You\n" +
                        "Ю\n" +
                        "את/אתה\n" +
                        "атА/Ат\n" +
                        "Ты\n" +
                        "You\n" +
                        "Ю(мн.ч)\n" +
                        "אתם/אתן\n" +
                        "атЭн/атЭм\n" +
                        "Вы\n" +
                        "He\n" +
                        "хИ\n" +
                        "הוא\n" +
                        "У\n" +
                        "Он\n" +
                        "She\n" +
                        "шИ\n" +
                        "היא\n" +
                        "И\n" +
                        "Она\n" +
                        "It\n" +
                        "Ит\n" +
                        "זה\n" +
                        "зЭ\n" +
                        "Это\n" +
                        "They\n" +
                        "зЭй\n" +
                        "הם/הן\n" +
                        "Эм\n" +
                        "Они\n" +
                        "Book\n" +
                        "бУк\n" +
                        "ספר\n" +
                        "сЭфэр\n" +
                        "Книга\n" +
                        "Good\n" +
                        "гУд\n" +
                        "טוב\n" +
                        "тОв\n" +
                        "Хорошо\n" +
                        "Start\n" +
                        "стАрт\n" +
                        "התחלה\n" +
                        "атхалЯ\n" +
                        "Начало\n" +
                        "End\n" +
                        "Энд\n" +
                        "סוף\n" +
                        "сОф\n" +
                        "Конец\n" +
                        "Victory\n" +
                        "вИктори\n" +
                        "ניצחון\n" +
                        "ницахОн\n" +
                        "Победа";
        String[] lib = words.split("\\r?\\n");
        this.library = new ArrayList<String>(Arrays.asList(lib));
    }
    private void saveLibrary(){
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/LanguageLearning";
            //file in specific path
            File file=new File(path+"/data.txt");
            //create new file
            file.createNewFile();
            //file writer
            FileWriter fw = new FileWriter(file,true);
            //write to file all content
            int i;
            for(i=0; i<this.library.size()-1; i++)
                fw.write(this.library.get(i)+"\n");
            fw.write(this.library.get(i)+"\n");
            fw.close();
        }
        catch (IOException e) {}
    }
    private void loadLibrary(){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/LanguageLearning";
        File dir = new File(path);
        dir.mkdirs();
        for(int i=0; i<numOfWords; i++) this.wordToRemove[i] = "";
        this.library = new ArrayList<String>();
        File file=new File(path+"/data.txt");
        FileInputStream fis;
        try{
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String word;
            while ((word=br.readLine()) != null)
                this.library.add(word);
        }catch(FileNotFoundException e2) {e2.printStackTrace();}
        catch(IOException e1) {e1.printStackTrace();}
    }
    public String[] getLevelWord(){
        return this.levelWord;}
    private void setLevelWord() {
        int index = (level - 1) * 5;
        if ((index >= 0) && ((index + 4) < this.library.size()))
            for (int i = 0; i < 5; i++)
                this.levelWord[i] = this.library.get(index + i);
    }
    public String[] getWordsToRemove(){
        setLevelWord();
        if(getLevelWord()!=null){
            for (int i = 0; i < numOfWords; i++) {
                if (i < 5) {
                    if (checkedLng[i])
                        wordToRemove[i] = levelWord[i];
                    else
                        wordToRemove[i] = this.library.get(getRandomIndex());
                } else {
                    wordToRemove[i] = this.library.get(getRandomIndex());
                }
            }
            return wordToRemove;
        }else
            return null;
    }
    public LevelMemento getMem() {
        return mem;
    }
    public void setMem(LevelMemento mem) {
        this.mem = mem;
    }
    public ArrayList<String> getSaves() {
        return this.saves;
    }
    public void setSaves(ArrayList<String> saves) {
        this.saves = saves;
    }

    //
    public String getWordInfo(){
        return this.levelWord[nativeLng];
    }
    private int getRandomIndex(){
        int index = (level - 1) * 5;
        Random rnd = new Random();
        int rndIndex;
        do {
            rndIndex = rnd.nextInt(this.library.size());
        }while (rndIndex/5==index/5 || !checkedLng[rndIndex%5]);
        return rndIndex;
    }
    public boolean checkLevelWord(int j, String word) {
        return word.equals(levelWord[j]);
    }
    public int getRemain() {
        int r = numOfWords;
        for (int i = 0; i < this.checkedLng.length; i++)
            if (this.checkedLng[i])
                r--;
        return r;
    }
    public void setMemento(){
        mem = new LevelMemento(SingletonPoint.getInstance().getUserName(), nativeLng, level, SingletonPoint.getInstance().getPoint(), checkedLng);
    }
    public void saveGame(){
        mem.save();
    }
    public void getMemento(){
        SingletonPoint.getInstance().setUserName(mem.getUserName());
        Library.getInstance().setNativeLng(mem.getNativeLng());
        Library.getInstance().setLevel(mem.getLevel());
        SingletonPoint.getInstance().setPoint(mem.getPoint());
        Library.getInstance().setCheckedLng(mem.getCheckedLng());
    }
    public void loadGame(){
        mem.loadSavedGames();
    }
    public void load(int k){
        mem.load(k);
    }
    public int getMemSize() {
        return mem.getSize();
    }
    public String getUserName(int k) {
        return mem.getUserName(k);
    }
    public String getLevel(int k) {
        return mem.getLevel(k);
    }

    public boolean isLoaded(){
        return mem.isLoaded();
    }
}
