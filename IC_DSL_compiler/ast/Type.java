package ast;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.regex.Pattern;

public enum Type {
    BALANCE("balance", true),
    PRICE("price", true),
    SLIPPAGE("slippage", true),
    FEE("fee", true),
    WALLET("wallet", true),

//    USDT("USDT", true),
//    USDC("USDC", true),
//    ETH("ETH", true),
//    DAI("DAI", true),
//    BTC("BTC", true),
//    WBTC("WBTC", true),
//    UNI("UNI", true),
//    SUSHI("SUSHI", true),
//    AAVE_token("AAVE", true),
//    MATIC("MATIC", true),
//    COMP("COMP", true),

    ASSET("USDT|USDC|ETH|DAI|BTC|WBTC|UNI|SUSHI|AAVE\\_token|MATIC|COMP", true),

    PLATFORM("AAVE|UNISWAP|COMPOUND|YEARN|SUSHISWAP|CURVE|ONEINCH|POLYGON|AVAX", true),

    BEFORE("before", true),
    AFTER("after", true),
    DURING("during", true),

    TIME("[0-9][0-9][0-9][0-9]-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])T([01]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]", false),
    KEY("0(x|X)[(0-9)|(A-F)|(a-f)]+", false),
    DEC_INT("0|([1-9][0-9]*)", false),
    DEC_FLOAT("([0-9]*\\.[0-9]*((p|P|e|E)(\\+|\\-)?[0-9]+)?)|" +
            "([0-9]*[\\.]?[0-9]*(p|P|e|E)((\\+|\\-)?[0-9]+)?)", false),
    IDENTIFIER("[A-Za-z_][A-Za-z0-9_]*", false),


    LOGIC_AND("and", true),
    LOGIC_OR("or", true),
    LOGIC_NOT("not", true),
    EQ("==", false),
    NEQ("!=", false),
    LT("<", false),
    GT(">", false),
    LE("<=", false),
    GE(">=", false),
    ADD("\\+", false),
    SUB("-", false),
    MUL("\\*", false),
    DIV("/", false),
    MOD("%", false),


    SEMI(";", false),
    COMMA(",", false),
    LPARENT("\\(", false),
    RPARENT("\\)", false),
    LBRACK("\\[", false),
    RBRACK("]", false),
    LBRACE("\\{", false),
    RBRACE("}", false);
    //    STR("", false);


    public static final ArrayList<Type> numTypeList =
            new ArrayList<>(Arrays.asList(DEC_INT, DEC_FLOAT));
    private String pattern;
    private boolean ifKeyword;

    Type(String pattern, boolean ifKeyword) {
        this.pattern = pattern;
        this.ifKeyword = ifKeyword;
    }

    //用于不同匹配识别不同词类型
    //(?!pattern)   非获取匹配，正向否定预查，在任何不匹配pattern的字符串开始处匹配查找字符串，该匹配不需要获取供以后使用。
    /*
    example:
    var testReg=/test(?!123)/;
    var result=testReg.exec('test123');
    console.log(result)//输出null

    var result2=testReg.exec('test12');
    console.log(result[0])//输出test
    */
//    public Pattern getPattern() {
//        return Pattern.compile("^(" + this.pattern + ")"
//                + (this.ifKeyword ? "(?![A-Za-z0-9_])" : ""));      //防止出现 将ifNew识别为if 这类错误
//    }
}
