/**
 * Created by atem1 on 24.04.2017.
 */
"use strict";
var vars = {
    'x': 0,
    'y': 1,
    'z': 2,
    'u': 3,
    'v': 4,
    'w': 5
};

for (var v in vars) {
    this[v] = new Variable(v);
}

var constants = {
    'pi': Math.PI,
    'e': Math.E
};
for (var c in constants) {
    this[c] = new Const(constants[c]);
}

var tokenOperation = {
    '+': Add,
    '-': Subtract,
    '*': Multiply,
    '/': Divide,
    'negate': Negate,
    'square': Square,
    'sqrt': Sqrt,
    'sin': Sin,
    'cos': Cos
};

var tokenCount = {
    '+': 2,
    '-': 2,
    '*': 2,
    '/': 2,
    'negate': 1,
    'square': 1,
    'sqrt': 1,
    'sin': 1,
    'cos': 1
};
var Zero = new Const(0);
var One = new Const(1);
var Two = new Const(2);

//=============\\
function Const(value) {
    this.value = value;
}

Const.prototype.evaluate = function () {
    return this.value;
};

Const.prototype.toString = function () {
    return '' + this.value;
};

Const.prototype.diff = function () {
    return Zero;
};

Const.prototype.prefix = function () {
    return '' + this.value;
};

Const.prototype.postfix = function () {
    return '' + this.value;
};
//=============//

//=============\\
function Variable(name) {
    this.name = name;
}

Variable.prototype.evaluate = function () {
    return arguments[vars[this.name]];
};

Variable.prototype.toString = function () {
    return this.name;
};

Variable.prototype.diff = function (name) {
    if (this.name === name) {
        return One;
    } else {
        return Zero;
    }
};

Variable.prototype.prefix = function () {
    return this.name;
};

Variable.prototype.postfix = function () {
    return this.name;
};
//=============//

//=============\\
function Operation() {
    var args = Array.prototype.slice.call(arguments);
    this.getArguments = function () {
        return args;
    }
}

Operation.prototype.evaluate = function () {
    var ans = [];
    var args = arguments;
    this.getArguments().forEach(function (value, i) {
        ans[i] = value.evaluate.apply(value, args);
    });
    return this.Action().apply(null, ans);
};

Operation.prototype.toString = function () {
    return this.getArguments().join(" ") + " " + this.Symbol();
};

Operation.prototype.prefix = function () {
    return "(" + this.Symbol() + ' ' + this.getArguments().map(function (value) {
            return value.prefix()
        }).join(" ") + ')';
};

Operation.prototype.postfix = function () {
    return "(" + this.getArguments().map(function (value) {
            return value.postfix()
        }).join(" ") + ' ' + this.Symbol() + ')';
};

function MakeOperation(action, symbol, Diff) {
    this.Action = function () {
        return action;
    };
    this.Symbol = function () {
        return symbol;
    };
    this.diff = function (x) {
        return Diff.call(this, x);
    };
}

MakeOperation.prototype = Operation.prototype;

//=============//

//=============\\
function Add() {
    Operation.apply(this, arguments);
}

Add.prototype = new MakeOperation(
    function (a, b) {
        return a + b;
    },
    '+',
    function (x) {
        var a = this.getArguments()[0];
        var b = this.getArguments()[1];
        return new Add(a.diff(x), b.diff(x));
    }
);
//=============//

//=============\\
function Subtract() {
    Operation.apply(this, arguments);
}

Subtract.prototype = new MakeOperation(
    function (a, b) {
        return a - b;
    },
    '-',
    function (x) {
        var a = this.getArguments()[0];
        var b = this.getArguments()[1];
        return new Subtract(a.diff(x), b.diff(x));
    }
);
//=============//

//=============\\
function Multiply() {
    Operation.apply(this, arguments);
}

Multiply.prototype = new MakeOperation(
    function (a, b) {
        return a * b;
    },
    '*',
    function (x) {
        var a = this.getArguments()[0];
        var b = this.getArguments()[1];
        return new Add(new Multiply(a.diff(x), b), new Multiply(a, b.diff(x)));
    }
);
//=============//

//=============\\
function Divide() {
    Operation.apply(this, arguments);
}

Divide.prototype = new MakeOperation(
    function (a, b) {
        return a / b;
    },
    '/',
    function (x) {
        var a = this.getArguments()[0];
        var b = this.getArguments()[1];
        return new Divide(new Subtract(new Multiply(a.diff(x), b), new Multiply(a, b.diff(x))), new Multiply(b, b));
    }
);
//=============//

//=============\\
function Negate() {
    Operation.apply(this, arguments);
}

Negate.prototype = new MakeOperation(
    function (a) {
        return -a;
    },
    'negate',
    function (x) {
        var a = this.getArguments()[0];
        return new Negate(a.diff(x));
    }
);
//=============//

//=============\\
function Square() {
    Operation.apply(this, arguments);
}

Square.prototype = new MakeOperation(
    function (a) {
        return a * a;
    },
    'square',
    function (x) {
        var a = this.getArguments()[0];
        return new Multiply(new Multiply(Two, a), a.diff(x));
    }
);
//=============//

//=============\\
function Sin() {
    Operation.apply(this, arguments);
}

Sin.prototype = new MakeOperation(
    function (a) {
        return Math.sin(a);
    },
    'sin',
    function (x) {
        var a = this.getArguments()[0];
        return new Multiply(new Cos(a), a.diff(x));
    }
);
//=============//

//=============\\
function Cos() {
    Operation.apply(this, arguments);
}

Cos.prototype = new MakeOperation(
    function (a) {
        return Math.cos(a);
    },
    'cos',
    function (x) {
        var a = this.getArguments()[0];
        return new Negate(new Multiply(new Sin(a), a.diff(x)));
    }
);
//=============//

//=============\\
function Sqrt() {
    Operation.apply(this, arguments);
}

Sqrt.prototype = new MakeOperation(
    function (a) {
        return Math.sqrt(Math.abs(a));
    },
    'sqrt',
    function (x) {
        var a = this.getArguments()[0];
        return new Multiply(new Multiply(new Divide(new Const(0.5), new Sqrt(a)), a.diff(x)), new Divide(new Divide(new Square(a), new Sqrt(new Square(a))), a));
    }
);
//=============//

function parse(expression) {
    var stack = [];
    var tokens = expression.trim().split(/(?:\s+)/);
    //console.info(tokens);
    for (var i = 0; i < tokens.length; i++) {
        var token = tokens[i];
        if (token in tokenOperation) {
            var arg = [];
            for (var j = 0; j < tokenCount[token]; j++) {
                arg.push(stack.pop());
            }
            arg.reverse();
            var x = Object.create(tokenOperation[token].prototype);
            tokenOperation[token].apply(x, arg);
            stack.push(x);
        } else if (token in constants) {
            stack.push(new Const(constants[token]));
        } else if (token in vars) {
            stack.push(new Variable(token));
        } else {
            stack.push(new Const(parseInt(token)));
        }
    }
    return stack.pop();
}

//=================\\
var expr = "";
var ind = 0;
var stack = [];
var index = [];

function myNew(constructor, args) {
    var tmp = Object.create(constructor.prototype);
    constructor.apply(tmp, args);
    return tmp;
}

function isGood(a) {
    return (a instanceof Const || a instanceof Variable || a instanceof Operation);
}

function skipWhiteSpace() {
    while (ind < expr.length && /\s/.test(expr.charAt(ind))) {
        ind++;
    }
}
function getNumber() {
    var res = "";
    if (expr.charAt(ind) === "-") {
        res += "-";
        ind++;
    }
    while (ind < expr.length && /\d/.test(expr.charAt(ind))) {
        res += expr.charAt(ind++);
    }
    return res;
}
function checkEmpty() {
    skipWhiteSpace();
    if (ind === expr.length) {
        throw new Error("Empty input");
    }
}
function getIdentifier() {
    if (!(/[A-Za-z]/.test(expr.charAt(ind)))) {
        throw new Error("Unknown symbol '" + expr.charAt(ind) + "' at position: " + ind);
    }
    var res = "";
    while (ind < expr.length && /\w/.test(expr.charAt(ind))) {
        res += expr.charAt(ind++);
    }
    return res;
}
function tryNumber() {
    var curNumber = getNumber();
    if (curNumber !== "" && curNumber !== "-") {
        return parseInt(curNumber);
    }
    if (curNumber === "-") {
        ind--;
    }
    return undefined;
}
function lastInStack() {
    return stack[stack.length - 1];
}

function doJob(mode) {
    var tempInd = undefined;
    var curOperation = undefined;
    var operands = [];
    if (mode) {
        if (!(lastInStack() in tokenOperation)) {
            throw new Error("Expected operation at position: " + ind);
        } else {
            curOperation = stack.pop();
            tempInd = index.pop();
        }
        var n = tokenCount[curOperation];
        for (var i = 0; i < n; i++) {
            var tmp = stack.pop();
            index.pop();
            if (!isGood(tmp)) {
                throw new Error("Too few operands for operation '" + curOperation + "' at position: " + tempInd);
            }
            operands[n - i - 1] = tmp;
        }
        tmp = stack.pop();
        if (tmp !== "(") {
            throw new Error("Too many operands for operation '" + curOperation + "' at position: " + tempInd);
        }
        index.pop();
        stack.push(myNew(tokenOperation[curOperation], operands));
    } else {
        while ((lastInStack() !== "(") && !(lastInStack() in tokenOperation)) {
            operands.push(stack.pop());
            index.pop();
        }
        if (lastInStack() === "(") {
            throw new Error("Expected operation at position: " + index.pop());
        }
        curOperation = stack.pop();
        tempInd = index.pop();
        if (stack.pop() !== "(") {
            throw new Error("Lose " + curOperation + "' at position: " + ind);
        }
        index.pop();
        if (operands.length > tokenCount[curOperation]) {
            throw new Error("Too many operands for operation '" + curOperation + "' at position: " + tempInd);
        } else if (operands.length < tokenCount[curOperation]) {
            throw new Error("Too few operands for operation '" + curOperation + "' at position: " + tempInd);
        } else {
            stack.push(myNew(tokenOperation[curOperation], operands.reverse()));
        }
    }
}

function parseAny(s, mode) {
    var balance = 0;
    ind = 0;
    expr = s;
    stack = [];
    checkEmpty();
    while (true) {
        skipWhiteSpace();
        if (ind >= expr.length) {
            break;
        }
        if (expr.charAt(ind) === ")") {
            balance--;
            if (balance < 0) {
                throw new Error("lose ')' at position: " + ind);
            }
            doJob(mode);
            ind++;
            if (balance === 0) {
                break;
            }
            continue;
        }
        index.push(ind);
        if (expr.charAt(ind) === "(") {
            stack.push("(");
            ind++;
            balance++;
            continue;
        }
        var curNumber = tryNumber();
        if (curNumber !== undefined) {
            stack.push(new Const(curNumber));
            continue;
        }
        var curOp = undefined;
        var curId;
        if (expr.charAt(ind) in tokenOperation) {
            curOp = expr.charAt(ind);
            ind++;
        } else {
            curId = getIdentifier();
            if (curId in tokenOperation) {
                curOp = curId;
            }
        }
        if (curOp !== undefined) {
            stack.push(curOp);
        } else if (curId in vars) {
            stack.push(new Variable(curId));
            if (balance === 0) {
                break;
            }
        } else {
            throw new Error("Unknown operand: " + curId + " at position: " + index.pop());
        }
    }
    skipWhiteSpace();
    if (ind !== expr.length) {
        throw new Error("Unexpected suffix after correct expression at position: " + ind);
    } else if (balance > 0) {
        throw new Error("lose ')' at position: " + ind);
    } else if (stack.length > 1) {
        throw new Error("Lose bracket for: '" + stack[0] + "' at position: " + index[0]);
    }
    var res = stack.pop();
    if (!isGood(res)) {
        throw new Error("Lose bracket for: '" + res + "' at position: " + index.pop());
    }
    return res;
}

function parsePrefix(s) {
    return parseAny(s, 0);
}

function parsePostfix(s) {
    return parseAny(s, 1);
}
//===============//
