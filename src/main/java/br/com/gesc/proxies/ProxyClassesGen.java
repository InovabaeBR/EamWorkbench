package br.com.gesc.proxies;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Date;

public class ProxyClassesGen {
    Date today = new Date();
    String todayMillis = Long.toString(today.getTime());
    String todayClass = "z_" + todayMillis;
    String todaySource = todayClass + ".java";
    String source = this.getClass().getClassLoader().getResource("").getPath().concat("//").concat(todaySource);

    public static void main(String args[]) {
        ProxyClassesGen mtc = new ProxyClassesGen();
        mtc.createIt();
        if (mtc.compileIt()) {
            System.out.println("Running " + mtc.todayClass + ":\n\n");
            mtc.runIt();
        } else
            System.out.println(mtc.todaySource + " is bad.");
    }

    public void createIt() {
        try {
            FileWriter aWriter = new FileWriter(source, true);
            aWriter.write("public class " + todayClass + "{\n");
            aWriter.write(" public void doit() {");
            aWriter.write(" System.out.println(\"" + todayMillis + "\");");
            aWriter.write(" }}\n");
            aWriter.flush();
            aWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean compileIt() {
        String[] sourceC = {new String(source)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, baos, null, sourceC);

        return (baos.toString().indexOf("error") == -1);
    }

    public void runIt() {
        try {
            Class params[] = {};
            Object paramsObj[] = {};
            Class thisClass = Class.forName(todayClass);
            Object iClass = thisClass.newInstance();
            Method thisMethod = thisClass.getDeclaredMethod("doit", params);
            thisMethod.invoke(iClass, paramsObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
