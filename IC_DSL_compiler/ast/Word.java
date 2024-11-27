package ast;

public class Word {
    private String content;
    private Type type;

    public Word(String content, Type type) {
        this.content = content;
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public String getContent() {
        if (this.content.equals("AAVE_token")) {
            return "AAVE";
        }

        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "<" + this.type + " : " + this.content + ">";
    }

    public boolean isIntConst() {
        return this.type.ordinal() == Type.DEC_INT.ordinal();
    }

    public boolean isFloatConst() {
        return this.type.ordinal() == Type.DEC_FLOAT.ordinal();
    }

}
