'use strict';
var vars = {
    'x': 0,
    'y': 1,
    'z': 2,
    'u': 3,
    'v': 4,
    'w': 5
};
for (var v in vars) {
    this[v] = variable(v);
}

var constants = {
    'pi': Math.PI,
    'e': Math.E
};
for (var c in constants) {
    this[c] = cnst(constants[c]);
}


function cnst(value) {
    return function () {
        return value;
    };
}

function variable(name) {
    return function () {
        return arguments[vars[name]];
    };
}

function operation(func) {
    return function () {
        var op = arguments;
        return function () {
            var ans = [];
            for (var i = 0; i < op.length; i++) {
                ans.push(op[i].apply(null, arguments));
            }
            return func.apply(null, ans);
        }
    }
}

var add = operation(function (a, b) {
    return a + b;
});

var subtract = operation(function (a, b) {
    return a - b;
});

var multiply = operation(function (a, b) {
    return a * b;
});

var divide = operation(function (a, b) {
    return a / b;
});

var negate = operation(function (a) {
    return -a;
});


var min3 = operation(function () {
    return Math.min.apply(null, arguments);
});

var max5 = operation(function () {
    return Math.max.apply(null, arguments);
});


function parse(expression) {
    var tokenOperation = {
        '+': add,
        '-': subtract,
        '*': multiply,
        '/': divide,
        'negate': negate,
        'min3': min3,
        'max5': max5
    };
    var tokenCount = {
        '+': 2,
        '-': 2,
        '*': 2,
        '/': 2,
        'negate': 1,
        'min3': 3,
        'max5': 5
    };
    var stack = [];
    var tokens = expression.trim().split(/\s+/);
    for (var i = 0; i < tokens.length; i++) {
        var token = tokens[i];
        if (token in tokenOperation) {
            var arg = [];
            for (var j = 0; j < tokenCount[token]; j++) {
                arg.push(stack.pop());
            }
            arg.reverse();
            stack.push(tokenOperation[token].apply(null, arg));
        } else if (token in constants) {
            stack.push(cnst(constants[token]));
        } else if (token in vars) {
            stack.push(variable(token));
        } else {
            stack.push(cnst(parseInt(token)));
        }
    }
    return stack.pop();
}



/*for (var i = -5; i <= 10; i++) {
 console.info(parse('x y + 2 /')(i, 2 * i));
 }//*/