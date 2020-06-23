package br.com.inovabae.jobs;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JobsUtilz {

    public static void main(String[] args) {
        String expression = "MC-DESV";
        String[] texts = {"PM-DESL", "PM-DESLO", "PM-DESV", "MC-DESV", "MC-APONT"};
        JobsUtilz utilz = new JobsUtilz();
        utilz.drilldownWithWildcards(expression, texts).forEach(System.out::println);
    }

    List<String> drilldownWithWildcards(String expression, String[] texts) {
        List<String> list = new ArrayList<String>();
        String string = StringUtils.split(expression, "\\*")[0];
        for (String text : texts) {
            if (text.contains(string)) {
                list.add(text);
            }
        }
        return list;
    }


}
