/**
*
* Serdar Arýcý - serdar.arici1@ogr.sakarya.edu.tr
* 29.03.2022
* <p>
* Bu sýnýfta istenilen verileri bulmaya yarayan regular expression ifadeleri oluþturuldu ve 
* istenilen verileri döngü kullanýlarak saymaya yarayan fonksiyonlar oluþturuldu.
* </p>
*/

package odev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexical {
	// programda kullanýlan sayaclar
	int tekliOperator = 0;
    int ikiliOperator = 0;
    int sayisalOperator = 0; // + , ++ , - , -- , * , / , % , & , | , ^ , = , += , -= , /= , *= , %= , &= , |= , ^=
    int sayisalOperatorKontrol = 0;
    int iliskiselOperator = 0; // < , <= , > , >= , == , !=
    int mantiksalOperator = 0; // && , || , !
    int mantiksalOperatorKontrol = 0; // !=
    int toplamOperand = 0;
	
    // programda kullanýlacak regEx kodlarý
    String sayisalRegex = "(\\+)|(\\+{2})|(\\-)|(\\-{2})|(\\*)|(\\/)|(\\%)|(\\&)|(\\|)|(\\^)|(\\=)|(\\+\\=)|(\\-\\=)|(\\/\\=)|(\\*\\=)|(\\%\\=)|(\\&\\=)|(\\|\\=)|(\\^=)";
    String sayisalRegexKontrol = "(\\&{2})|(\\={2})|(\\|{2})|(!=)|(<=)|(>=)";
    String tekliRegex = "(\\+{2})|(\\-{2})";
    String iliskiselRegex = "(\\<)|(\\<\\=)|(\\>)|(\\>\\=)|(\\={2})|(\\!\\=)"; 
    String mantiksalregex = "((\\|{2})|(!)|(\\&{2}))";
    String mantiksalRegexKontrol = "(!=)";
    
 // sayýsal operatorleri bulmaya yaran ve her bulduðunda sayýyý bir arrtýran fonksiyon
    public int sayisal(String satir) {
        // + , ++ , - , -- , * , / , % , & , | , ^ , = , += , -= , /= , *= , %= , &= , |= , ^=
        Pattern pattern = Pattern.compile(sayisalRegex);  // "(\\+)|[+]{2}|(-)|[-]{2}|(\\*)|(/)|(%)|(&)|(|)|(/^)|(=)|(\\+=)|(-=)|(/=)|(\\*=)|(%=)|(&=)|(|=)|(^=)"
        Matcher matcher = pattern.matcher(satir);

        while (matcher.find()) {
                sayisalOperator++;
        }

        sayisalOperator -= sayisalOperatorKontrol;
        return sayisalOperator;

    }
    
    // sayýsal operator sayýsýnýn yanlýþ olmamasý için bazý kontrollerin yapýldýðý fonksiyon
    public int sayisalKontrol(String satir){
        Pattern pattern = Pattern.compile(sayisalRegexKontrol);
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
            sayisalOperatorKontrol++;
        }
        return sayisalOperatorKontrol;
    }

    // tekli operatorleri bulmaya yaran ve her bulduðunda sayýyý bir arrtýran fonksiyon
    public int tekli(String satir){
        Pattern pattern = Pattern.compile(tekliRegex);
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
                tekliOperator++;
        }
        return tekliOperator;
    }
    
 // ikili operator sayýsýný bulmaya yarayan fonksiyon
    public int ikili(){
        ikiliOperator = sayisalOperator-tekliOperator;
        return ikiliOperator;
    }
    
 // iliskisel operatorleri bulmaya yaran ve her bulduðunda sayýyý bir arrtýran fonksiyon
    public int iliskisel(String satir){   // < , <= , > , >= , == , !=
        Pattern pattern = Pattern.compile(iliskiselRegex);   
        Matcher matcher = pattern.matcher(satir);

            while (matcher.find()) {
                iliskiselOperator++;
            }
        return iliskiselOperator;
    }
    
 // mantýksal operatorleri bulmaya yaran ve her bulduðunda sayýyý bir arrtýran fonksiyon
    public int mantiksal(String satir){
        Pattern pattern = Pattern.compile(mantiksalregex);
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
            mantiksalOperator++;
        }
        mantiksalOperator -= mantiksalOperatorKontrol;
        return mantiksalOperator;
    }

    // mantýksal operator sayýsýnýn yanlýþ olmamasý için bazý kontrollerin yapýldýðý fonksiyon
    public int mantiksalKontrol(String satir){
        Pattern pattern = Pattern.compile(mantiksalRegexKontrol); 
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
            mantiksalOperatorKontrol++;
        }
        return mantiksalOperatorKontrol;
    }

    // operand sayýsýný bulmaya yarayan fonksiyon
    public int operand(String satir){
        toplamOperand = ikiliOperator*2 + tekliOperator + iliskiselOperator*2 + mantiksalOperator*2;
        return  toplamOperand;
    }
	
}
	