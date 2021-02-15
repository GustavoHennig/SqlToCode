/*
 * IType.java
 *
 * Created on 13/08/2007, 14:07:23
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltocode;

/**
 * TODO: Instead of fixed types, use configurable types
 *
 * @author GustavoHennig
 */
public class VariableType {

    private String concs;
    private String conce;
    private String varType;
    private String varName = "sql";
    private String newInstanceExp;
    private StringBuffer out = new StringBuffer();
    private boolean DeclareVariable = false;

    public VariableType(String tipo) {

        varType = tipo;
        if (tipo.equalsIgnoreCase("String")) {
            concs = " += ";
            conce = ";";
            newInstanceExp = "\"\";";
        } else if (tipo.equalsIgnoreCase("StringBuffer")) {
            concs = ".append(";
            newInstanceExp = "new StringBuffer();";
            conce = ");";
        } else if (tipo.equalsIgnoreCase("StringBuilder")) {
            concs = ".append(";
            conce = ");";
            newInstanceExp = "new StringBuilder();";
        } else if (tipo.equalsIgnoreCase("char[]")) {
            concs = "";
        }
    }

    public String getDeclare() {

        String ret;

        ret = varType + " " + varName + " = " + newInstanceExp;

        return ret;
    }

    public String ConvertText(String source) {

        String tmp = source.replaceAll("\n\r", "\n");
        String[] vet = tmp.split("\n");

        out = new StringBuffer();

        if (DeclareVariable) {
            out.append(this.getDeclare());
            out.append("\n");
        }

        out.append(ConvertLine("", true));

        for (String v : vet) {
            out.append(ConvertLine(v, false));
        }

        return out.toString();
    }

    public void setDeclare(boolean selected) {
        this.DeclareVariable = selected;
    }

    public void setVarName(String value) {

        this.varName = value;
    }

    private String ConvertLine(String in, boolean first) {
        String ret;

        if (first && (varType.equalsIgnoreCase("String"))) {
            ret = "    " + varName + " = \" " + in + " \\n\"" + conce + "\n";
        } else {
            ret = "    " + varName + concs + "\" " + in + " \\n\"" + conce + "\n";
        }
        return ret;
    }
    /*
    public void BatchAddLine(String line) {
    out.append(line);
    }
    public String BatchGetResult() {
    }*/
    // public String getS;
}
