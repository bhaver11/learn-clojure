(ns clojure-noob.proj-euler)

;;problem 1
;;sum of all multiples of x and y below n

(defn sum-multiples
  [x y n]
  (reduce +
          (filter ;;condition for multiples of x and y
           #(or (zero? (rem % x))
                (zero? (rem % y)))
           (range 1 n))))


;;problem 2
;;sum of even-valued terms in fibonacci sequence upto 4 million

(defn lazy-fib
  "generates a lazy sequence of fibonacci numbers"
  ([]
   (lazy-fib 1 2))
  ([a b]
   (lazy-seq (cons a
                   (lazy-fib b (+ a b))))))

(defn fib-sum
  "returns the sum of even-valued tersm in fibonacci sequence upto limit"
  [limit]
  (reduce +
          (filter #(even? %)
                  (take-while #(< % limit) (lazy-fib)))))



;;problem 3 largest prime factor second try
(defn larg-prime-factor
  ([num]
   (larg-prime-factor num 2 (Math/sqrt num)))
  ([num n sqr-root]
   (println num n sqr-root)
   (if  (<= num sqr-root)
     (if (= num 1)
       n

       num)
     (if (= 0 (mod num n))
       (larg-prime-factor (/ num n) n sqr-root)
       (larg-prime-factor num (inc n) sqr-root)))))


;;problem 4
;;largest palindrome by multiplication of two 3 digit numbers

(defn palin?
  "returns true if a number is a palindrome, false otherwise"
  [num]
  (= (str num)
     (clojure.string/reverse (str num))))



(defn prob3
  "returns the largers palindrome formed by multiplication of two 3 digit numbers."
  []
  (apply max (for [i (range 100 1000)
                   j (range 100 1000)
                   :let [mult (* i j)]
                   :when (palin? mult)]
               mult)))





;;problem 5
(defn gcd
  [a b]
  (if (= 0 b)
    a
    (if (= 0 a)
      b
      (gcd b (mod a b)))))


(defn lcm
  [a b]
  (/ (* a b) (gcd a b)))

(defn lcm-arr
  [arr]
  (reduce lcm arr))

(lcm-arr (range 1 21))

;;problem 6
(defn sum-square
  []
  (let [sum-square (reduce + (map #(* % %) (range 1 101)))
        sum (reduce + (range 1 101))]
    (- (* sum sum) sum-square)))

;;problem 7
(defn isprime?
  [n]
  (if (or (= n 0) (= n 1))
    true
    (some #(= 0 (mod n %)) (range 2 (/ n 2)))))


(defn nth-prime
  [n]
  (last (take (+ n 1) (filter #(not (isprime? %)) (range)))))


;;problem 9, pythagorean triplets whose sum is 10000
(defn pyth
  "Returns product of pythagoream triplets with given sum"
  [sum]
  (for [a (range 1 (/ sum 2))
                  b (range (inc a) (/ sum 2))
                  :let [c (- sum a b)]
                  :when (= (* c c)
                           (+ (* a a) (* b b)))]
             (* a b c)))



