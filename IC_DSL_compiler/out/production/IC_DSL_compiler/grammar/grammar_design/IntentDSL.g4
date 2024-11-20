grammar IntentDSL;

program: triggerStatement+;

//----------------------------------------------------------------------//

LPARENT : '(';
RPARENT : ')';
LBRACK : '[';
RBRACK : ']';

LOGIC_AND : 'and';
LOGIC_OR : 'or';
LOGIC_NOT : 'not';

EQ : '==';
NEQ : '!=';
LT : '<';
GT : '>';
LE : '<=';
GE : '>=';

ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
MOD : '%';

BALANCE : 'balance';
PRICE : 'price';
SLIPPAGE : 'slippage';
FEE : 'fee';
WALLET : 'wallet';

USDT : 'USDT';
USDC : 'USDC';
ETH : 'ETH';
DAI : 'DAI';
BTC : 'BTC';
WBTC : 'WBTC';
UNI : 'UNI';
SUSHI : 'SUSHI';
AAVE_token : 'AAVE';
MATIC : 'MATIC';
COMP : 'COMAP';

AAVE : 'Aave';
UNISWAP : 'Uniswap';
COMPOUND : 'Compound';
YEARN : 'Yearn';
SUSHISWAP : 'Sushiswap';
CURVE : 'Curve';
ONEINCH : '1inch';
POLYGON : 'Polygon';
AVAX : 'Avax';

IDENTIFIER : [A-Za-z_][A-Za-z0-9_]*;

KEY : '0'('x'|'X')[0-9A-Fa-f]+;

DEC_INT : '0' | ([1-9][0-9]*);
DEC_FLOAT
    : [0-9]*'.'[0-9]*(('p'|'P'|'e'|'E')('+'|'-')?[0-9]+)?
    | [0-9]+[.]?[0-9]*('p'|'P'|'e'|'E')(('+'|'-')?[0-9]+)?;

TIME : [0-9][0-9][0-9][0-9] '-' ('0'[1-9] | '1'[0-2]) '-' ('0'[1-9] | [12][0-9] | '3'[01]) 'T' ([01]?[0-9] | '2'[0-3]) ':' [0-5]?[0-9] ':' [0-5]?[0-9];

SEMI : ';';
LINE_COMMENT : '//' .*? '\r'?'\n' -> skip;
COMMENT : '/*' .*? '*/' -> skip;
BLANK : [ \t\r\n]+ -> skip;

//----------------------------------------------------------------------//

condition
    : orExpression;
timeCondition
    : 'time before' TIME
    | 'time after' TIME
    | 'time during' TIME 'to' TIME;
orExpression
    : andExpression (LOGIC_OR andExpression)*;
andExpression
    : (comparisonExpression | timeCondition)(LOGIC_AND (comparisonExpression| timeCondition))*;
comparisonExpression
    : binaryExpression (comparisonOperator binaryExpression)?;
binaryExpression
    : lowBinaryExpression (highBinaryOperator lowBinaryExpression)*;
lowBinaryExpression
    : unaryExpression (lowBinaryOperator unaryExpression)*;
unaryExpression
    : (unaryOperator)* primaryExpression;
binaryOrUnaryExpression : binaryExpression | unaryExpression;
primaryExpression
    : walletBalance
    | assetPrice
    | number asset
    | SLIPPAGE
    | FEE
    | number
    | LPARENT binaryOrUnaryExpression RPARENT;

logicalOperator
    : LOGIC_AND | LOGIC_OR;

comparisonOperator
    : EQ | NEQ | LT | GT | LE | GE;

highBinaryOperator
    : MUL | DIV | MOD;

lowBinaryOperator
    : ADD | SUB;

unaryOperator
    : ADD | SUB | LOGIC_NOT;

number
    : DEC_INT | DEC_FLOAT;


//----------------------------------------------------------------------//

triggerStatement
    : ('trigger' condition 'then')? statement ('checking' condition)? SEMI;

statement
    : transferStatement
    | borrowStatement
    | repayBorrowStatement
    | stakeStatement
    | swapStatement
    | addLiquidityStatement
    | removeLiquidityStatement
    ;

transferStatement
    : 'transfer' amount 'from' wallet 'to' wallet;

borrowStatement
    : 'borrow' amount 'for' wallet 'from' platform 'using' amount 'from' wallet 'as collateral';

repayBorrowStatement
    : 'repay' amount 'from' wallet 'to' platform;

stakeStatement
    : 'stake' amount 'from' wallet 'to' platform;

swapStatement
    : 'swap' amount 'from' wallet 'for' asset 'on' platform;

addLiquidityStatement
    : 'add liquidity' amount 'from' wallet (','amount 'from' wallet)* 'to' platform;

removeLiquidityStatement
    : 'remove liquidity' amount 'to' wallet (','amount 'to' wallet)* 'from' platform;


walletBalance
    : BALANCE wallet;

assetPrice
    : PRICE asset 'on' platform;

amount
    : binaryExpression asset;

asset
    : USDT | USDC | ETH | DAI | BTC | WBTC | UNI | SUSHI | AAVE_token | MATIC | COMP;

pair
    : asset 'and' asset;

wallet
    : WALLET LBRACK KEY RBRACK;

platform
    : AAVE | UNISWAP | COMPOUND | YEARN | SUSHISWAP | CURVE | ONEINCH | POLYGON | AVAX;