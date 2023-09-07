package dao;
import java.io.IOException;

/**
 *
 * @author ENG JIA JEAN
 */
public class StudTutProgInitializer {

    //Will be used when there's no file
    private StudentDAO studDao = new StudentDAO();
    private ProgDAO progDao = new ProgDAO();
    private TutGrpDAO tutDao = new TutGrpDAO();
    private HistoryDAO historyDao = new HistoryDAO();

    public void studInit() throws IOException {
        if (!studDao.checkFileExist()) {
            String str = "23WMR00001,JiaJean,20,3.88,RSWG7\n"
                    + "23WMR00002,Cutie,20,3.3,RSWG6\n"
                    + "23WMR00003,Poppy,20,3.87,RSWG7\n"
                    + "21PMD09347,JJ,20,3.23,RSWG7\n"
                    + "23PMR09546,Chao,18,3.65,RSWG7\n"
                    + "22PMD09876,DK,21,3.89,RSTG1\n"
                    + "23WMR10345,Kled,21,4.0,RSWG6\n"
                    + "23WMR00009,Kongyue,24,1.0,RSWG7\n";
            studDao.saveToFile(str);
        }
    }

    public void tutInit() throws IOException {
        if (!tutDao.checkFileExist()) {
            String str = "RSWG7,5,RSW\n"
                    + "RSWG6,2,RSW\n"
                    + "RSTG1,1,RST\n";
            tutDao.saveToFile(str);
        }
    }

    public void progInit() throws IOException {
        if (!progDao.checkFileExist()) {
            String str = "RSW,Software Engineering,SE,200\n"
                    + "RST,Internet Security,IS,200\n";
            progDao.saveToFile(str);
        }
    }
    
    public void historyInit() throws IOException {
        if(!historyDao.checkFileExist()){
            historyDao.saveToFile("");
        }
    }

}
