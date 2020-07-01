package br.com.gesc.tests;

import org.apache.commons.lang3.StringUtils;

public class GeneralTests {

    public static void main(String[] args) {
        String str = "MC-*";

        for (String s : StringUtils.split(str, "\\*")) {
            System.out.println(s);
        }
    }
}
