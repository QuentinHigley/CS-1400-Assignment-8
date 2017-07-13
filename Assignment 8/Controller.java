package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Scanner;

public class Controller {

    public Button btnChooseFile;
    public TextArea txtOutput;



    public void ChooseFile()
    {
        //declare fileChooser
        FileChooser fc = new FileChooser();
        //filter out non-texFiles
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)","*.txt");
        fc.getExtensionFilters().add(extFilter);
        File SelectedFile = fc.showOpenDialog(null);
        //let the Fun begin
        if (SelectedFile != null)
        {
            String strInput = readFile(SelectedFile);
            StringBuilder strEncryptionAlpha = new StringBuilder();
            StringBuilder strEncryptionNum = new StringBuilder();

            for(int x = 0; x < strInput.length(); x++)
            {
                //first encryption bump all letters by 1
                //guessing Z becomes A but unsure
                int intEncrypt;
                char charEncrypt;
                charEncrypt = strInput.charAt(x);
                intEncrypt = (int) charEncrypt;

                if (intEncrypt > 64 && intEncrypt < 90 || intEncrypt > 96 && intEncrypt < 122)
                {
                    intEncrypt++;
                }
                //sets z = a
                else if(intEncrypt == 122)
                {
                    intEncrypt = 97;
                }
                //sets Z = A
                else if(intEncrypt == 90)
                {
                    intEncrypt = 65;
                }
                charEncrypt = (char) intEncrypt;
                strEncryptionAlpha.append(charEncrypt);
            }

            //second encryption
            char[] charAlpha = strInput.toCharArray();
            for(char charNum : charAlpha)
            {
                int intTemp = (int) charNum;
                int intTemp2;
                //if it is in this range it is a lower case number
                if(intTemp <= 122 && intTemp >= 97 )
                {
                    intTemp2 = intTemp - 96;
                    if(intTemp2 < 10)
                    {
                        strEncryptionNum.append("0"+intTemp2);
                    }
                    else
                    {
                        strEncryptionNum.append(intTemp2);
                    }
                }
                //if it is in this range it is an uppercase number
                else if(intTemp <= 90 && intTemp >= 65)
                {
                    intTemp2 = intTemp - 38;
                    strEncryptionNum.append(intTemp2);
                }
                //if in this range it is a number
                //ps if there is a way to do this without a switch or an if I would love to know
                else if(intTemp >= 48 && intTemp <=57)
                {
                    switch (intTemp)
                    {
                        case 48:  strEncryptionNum.append("Ze");
                        break;
                        case 49:  strEncryptionNum.append("On");
                        break;
                        case 50:  strEncryptionNum.append("Tw");
                        break;
                        case 51:  strEncryptionNum.append("Th");
                        break;
                        case 52:  strEncryptionNum.append("Fo");
                        break;
                        case 53:  strEncryptionNum.append("Fi");
                        break;
                        case 54:  strEncryptionNum.append("Si");
                        break;
                        case 55:  strEncryptionNum.append("Se");
                        break;
                        case 56:  strEncryptionNum.append("Ei");
                        break;
                        case 57:  strEncryptionNum.append("Ni");
                        break;

                    }
                }
                //adds all the other characters
                else
                {
                    strEncryptionNum.append(charNum);
                }
            }

            txtOutput.setText("Original:" + "\n" + strInput + "\n" +
                    "Encryption 1" + "\n" + strEncryptionAlpha.toString() + "\n" +
                    "Encryption 2" + "\n" + strEncryptionNum.toString());
        }
        else
        {
            System.out.println("none selected");
        }
    }

    private String readFile(File SelectedFile)
    {
        StringBuilder strText = new StringBuilder();
        BufferedReader Reader = null;
        //hopefully doesn't crash
        try
        {
            Reader = new BufferedReader(new FileReader(SelectedFile));

            String text;
            while ((text = Reader.readLine()) != null)
            {
                strText.append(text);
            }
        }
        catch(FileNotFoundException e)
        {
            txtOutput.setText("FileNotFound");
        }
        catch(IOException e)
        {
            txtOutput.setText("Error set another file");
        }
        finally {
            try{
                Reader.close();
            }
            catch (IOException ex)
            {
                txtOutput.setText("Error set another file");
            }
        }
        return strText.toString();
    }
}
