package no9;

import java.util.Map;

/**
 * Created by twb on 2017/5/17.
 */
public class FileMock {
    private String content[];
    private int index;

    public FileMock(int size, int length){
        content = new String[size];
        for(int i = 0; i < size; i++){
            StringBuilder sb = new StringBuilder(length);

            for(int j = 0; j < length; j++){
                int indice = (int)(Math.random() * 255);
                sb.append((char)indice);
            }
            content[i] = sb.toString();
        }
        index = 0;
    }
    public boolean hasMoreLines(){
        return index < content.length;
    }

    public String getLine(){
        if(this.hasMoreLines()){
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }
}
