import java.io.File;
import java.io.FileReader;

public class Word {
    int syn;//字类别
    /*
    6:关键字
    7：标识符

     */
    private String []Keyword={"int","main","double","float","include","if","else","break"};//关键字数组
    private char  ch;//读入的字符
    /*
    判断是否是关键字
     */
    boolean IsKeyword(String st){
        for (int i = 0; i <Keyword.length ; i++) {
            if (Keyword[i].equals(st)){
                return true;
            }
        }
        return false;
    }

    /*
    判断是否是字母
     */
    boolean IsLetter(char letter){
        if ((letter>='a'&& letter<='z') || (letter>='A' && letter <='Z')){
            return true;
        }
        return false;
    }

    /*
    判断是否是数字
     */
    boolean IsDigit(char c){
        if (c>='0'&& c<='9'){
            return true;
        }
        else
            return false;
    }


    /*
    词法分析
     */
    void WordAly(char[]chars){
        String string="";//新构成的字符串
        for (int i = 0; i <chars.length ; i++) {
            ch=chars[i];
            string="";
            if (ch==' '|| ch=='\t' || ch=='\n' || ch=='\r'){
//                ch=chars[++i];
            }
            else if (IsLetter(ch)){
                while (IsLetter(ch)||IsDigit(ch)){
                    string+=ch;
                    ch=chars[++i];
                }
                /*
                后退一个字符，因为刚才的字符判断完成后要判断紧接的下一个，
                但是此时字符已经跳到新的字符上，如果循环再进行一次i++,则会漏过一个字符
                 */
                i--;

                if (IsKeyword(string)){
                    syn=6;
                    System.out.println("< "+syn+","+string+">");
                }
                else{
                    syn=7;
                    System.out.println("<"+syn+","+string+">");
                }
            }
            else  if (IsDigit(ch)||ch=='.'){
                while (IsDigit(ch)||ch=='.'&& IsDigit(chars[++i])){
                    if (ch=='.')
                    i--;//条件判断中的i为了判断多加了一次，所以此处要复位到原来要判断的字符位置上
                    string+=ch;
                    ch=chars[++i];
                    syn=12;//常数

                }
                System.out.println("<"+syn+","+string+">");
            }
            else switch (ch){
                //3.运算符
                    case '+':
                        System.out.println("< 36,"+ch+">"); break;
                    case '-':
                        System.out.println("< 37,"+ch+">");break;
                    case '*':
                        System.out.println("< 38,"+ch+">");break;
                    case '/':
                        System.out.println("< 39,"+ch+">");break;
                    //4.分界符

                    case '(':
                        System.out.println("<61,"+ch+">");break;
                    case ')':
                        System.out.println("<62,"+ch+">");break;
                    case '{':
                        System.out.println("<63,"+ch+">");break;
                    case '}':
                        System.out.println("<64,"+ch+">");break;
                    case '[':
                        System.out.println("<65,"+ch+">");break;
                    case ']':
                        System.out.println("<66,"+ch+">");break;
                    case ';':
                        System.out.println("<67,"+ch+">");break;

                    //运算
                    case '=':
                        if (chars[++i]=='='){
                            System.out.println("<24,="+ch+">");

                            break;
                        }else {
                            System.out.println("<51,"+ch+">");
                            i--;
                            break;

                        }
                    case '>':
                        if (chars[++i]=='='){
                            System.out.println("<25,="+ch+">");

                            break;
                        }else {
                            System.out.println("<52,"+ch+">");
                            i--;break;
                        }
                    case '<':
                        if (chars[++i]=='='){
                            System.out.println("<25,"+ch+">");
                            break;
                        }else {
                            System.out.println("<53,"+ch+">");
                            i--;break;
                        }
                        default:
                            System.out.println("<--"+ch+"--无识别>");
            }
        }
    }

public static void main(String[] args) throws Exception{
        File file=new File("E:\\编译原理上机\\demo1.txt");
    FileReader reader=new FileReader(file);
    int len= (int) file.length();
    char temp[]=new char[len+1];
    reader.read(temp);
    reader.close();
    new Word().WordAly(temp);

        }
        }
