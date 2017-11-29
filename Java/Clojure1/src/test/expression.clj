(defn operator [func]
  (fn [& args]
    (fn [vars]
      (apply func (map (fn [x] (double (x vars))) args)))))

(defn / [a b] (/ (double a) (double b)))

(def add (operator +))
(def subtract  (operator -))
(def multiply  (operator *))
(def divide    (operator /))
(def negate    (operator -))
(def sinh (operator (fn [x] (Math/sinh x))))
(def cosh  (operator (fn [x] (Math/cosh x))))
(def sin  (operator (fn [x] (Math/sin x))))
(def cos  (operator (fn [x] (Math/cos x))))
(def square  (operator (fn [x] (* x x))))
(def sqrt  (operator (fn [x] (Math/sqrt (Math/abs (double x))))))

(defn constant [v]
  (fn [vars] v))

(defn variable [nam]
  (fn [vars] (get vars nam)))

(defn parseFunction [expression]
  (let [op {'+ add '- subtract '* multiply '/ divide 'sin sin 'cos cos 'sinh sinh 'cosh cosh 'sqrt sqrt 'square square 'negate negate}]
    (cond
      (string? expression) (parseFunction (read-string expression))
      (seq? expression)
      (let [exp (first expression)] (apply (get op exp) (map parseFunction (rest expression))))
      (number? expression) (constant expression)
      (symbol? expression) (variable (str expression)))))