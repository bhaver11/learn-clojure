(ns clojure-noob.core-test
  (:require #_[clojure.test :refer :all]
            [clojure-noob.core :refer :all]
            [clojure-noob.proj-euler :refer :all]
            #_[clojure-noob.peg-game :refer :all]
            [expectations :as expect]))

#_(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(expect/expect nil? nil)

;;mysortby tests

(expect/expect ["a" "bb" "ccc" ] (mysortby count ["bb" "a" "ccc"]))

(expect/expect ["a" "b" "cc" "cc" "ddd" "eee"] (mysortby count ["cc" "a" "ddd" "cc" "b" "eee"]))

;;all-cat tests

(expect/expect [1 2 3 3 4 5 6] (all-cat [1] [2 3 3] [4 5] [6]))

(expect/expect [44 55 2 3 4 [1 2] 22] (all-cat [44 55 2 3 4] [[1 2]] [22]))

;;my-min tests
(expect/expect 4 (my-min 66 77 8 9 4))

(expect/expect 1 (my-min 4 5 1 1 2 3))

;;my-sort tests

(expect/expect [1 2 3 4] (my-sort [4 3 2 1]))

(expect/expect [11 11 11 12 88 88] (my-sort [11 88 11 12 88 11]))

;;my-some tests

(expect/expect true (my-some odd? [1 2 3 4]))

(expect/expect nil (my-some empty? [[1] [2] [33 4]]))

;;my-filter tests

(expect/expect [0 0 0] (my-filter zero? [1 2 3 0 4 0 10 0]))

(expect/expect [[1 2] [2] [99]] (my-filter not-empty? [[1 2] [] [2] [] [] [99]]))

;;my-take-while tests

(expect/expect [1 3 5] (my-take-while odd? [1 3 5 4 2 1 3]))

(expect/expect ["aa" "bb"] (my-take-while #(if (= (count %) 2) true false) ["aa" "bb" "ccc" "bb"]))

;;my-take tests

(expect/expect [1 2 3] (my-take 4 [1 2 3]))

(expect/expect [] (my-take -1 [1 2 3 4]))

;;my-drop tests

(expect/expect [3 2 3] (my-drop 3 [1 1 7 3 2 3]))


;;my-reduce test

(expect/expect 12 (my-reduce + [1 2 3 6]))

(expect/expect 36 (my-reduce * [1 2 3 6]))

;;my-map test

(expect/expect [3 2 1 4] (my-map count ["aaa" "bb" "q" "1234"]))

(expect/expect [true false true true] (my-map odd? [1 2 5 7]))

(expect/expect [1 7] (remove-odd [1 1 5 7]))

(expect/expect 7 (mult-int 2 3))


(expect/expect 10 (add-dec 1 2 3 5))

(expect/expect 2 (multyfn 5 2))

#_(expect/expect true (triangular? 10))


(expect/expect [1 2 3 5 8] (fib-not-great 10))

(expect/expect 10 (sum-fib-even 10))

#_(expect/expect [2 3 5 7 11] (prime-nos 12))

(expect/expect true (prime? 17))

(expect/expect false (prime? 35))

#_(expect/expect [2 3 5 7] (prime-list 20))

#_(expect/expect [2 3 5 7] (prime-list 14))

(expect/expect [1 2 4 5 10] (factor-list 20))

(expect/expect 29 (largest-prime-factor 13195))


#_(expect/expect true (palindrome? 123321))





;; new test cases

;; first problem, multiples of 3 and 5 below 1000

(expect/expect 233168 (sum-multiples 3 5 1000))

(expect/expect 78 (sum-multiples 3 5 20))

(expect/expect 110110 (sum-multiples 7 11 1000))

;; second problem, sum of even-valued terms in fibonacci below 4 million
(expect/expect 4613732 (fib-sum 4000000))


;;third problem, palindrome

(expect/expect true (palin? 44))

(expect/expect false (palin? 998001))

(expect/expect 906609 (prob3))



(expect/expect 5 (larg-prime-factor 15))

(expect/expect 5 (gcd 10 5))

(expect/expect 1 (gcd 5 7))

(expect/expect 2520 (lcm-arr (range 1 11)))

(expect/expect true (isprime? 17))

(expect/expect false (isprime? 35))

(expect/expect 60 (pyth 12))

(expect/expect 31875000 (pyth 1000))

(def num8 73167176531330624919225119674426574742355349194934969835203127745063262395783180169848018694788518438586156078911294949545950173795833195285320880551112540698747158523863050715693290963295227443043557668966489504452445231617318564030987111217223831136222989342338030813533627661428280644448664523874930358907296290491560440772390713810515859307960866701724271218839987979087922749219016997208880937766572733300105336788122023542180975125454059475224352584907711670556013604839586446706324415722155397536978179778461740649551492908625693219784686224828397224137565705605749026140797296865241453510047482166370484403199890008895243450658541227588666881164271714799244429282308634656748139191231628245861786645835912456652947654568284891288314260769004224219022671055626321111109370544217506941658960408071984038509624554443629812309878799272442849091888458015616609791913387549920052406368991256071760605886116467109405077541002256983155200055935729725716362695618826704282524836008232575304207529634)

(expect/expect 23514624000 (prob8 13 num8))



(expect/expect [10 1 2 5] (n-divisors 10))


#_(expect/expect 89.5 (atm "problem1"))

#_(expect/expect 4 (inputs-div-k "problem2"))


#_(expect/expect '(0 14 24 253 5861 2183837) (prob3-cc))

(expect/expect [1 0 2 0 3] (my-interpose [1 2 3]))

(expect/expect [1 2 4 5 7 8] (drop-nth [1 2 3 4 5 6 7 8 9] 3))


(expect/expect '((1 3 5) '(2 4 6) (rev-interleave [1 2 3 4 5 6] 2)))

(expect/expect '((1 4 7) (2 5 8) (3 6 9)) (rev-interleave [1 2 3 4 5 6 7 8 9] 3))


(expect/expect [3 4 5 1 2] (rotate 2 [1 2 3 4 5]))

(expect/expect [4 5 1 2 3] (rotate -2 [1 2 3 4 5]))

(expect/expect [1 2 3 4 5] (rotate 5 [1 2 3 4 5]))

#_(expect/expect "Mahatma" (cap-first  "mahaTMa"))

(expect/expect "M. Gandhi" (trim-string ["mohandas" "gandhi"]))



(expect/expect "M. K. Gandhi" (trim-string ["moHandas" "karamchand" "gandhi"]))



(expect/expect '(0.0 1.5707963267948966) (angles-in-rad 3 0))

(expect/expect '(2.53 2.09 (angles-in-rad 10 55)))

#_(expect/expect '(0 270) (time-to-angle '("3" "0")))

#_(expect/expect '(120 145.41 (time-to-angle '(10 55))))

(expect/expect [10 19] (co-ordinates 7.8 9 ))

(expect/expect [16 10] (co-ordinates 6.2 6))

(def array [[1 0 1 1] [1 0 0 1] [0 1 1 0] [1 0 1 0]])

(expect/expect 1 (get-neighbours array 0 0))

(expect/expect 5 (get-neighbours array 1 1))


(expect/expect [[1 0 1] [1 0 1] [1 0 1]] (upd-board [[1 0 1] [1 1 1] [0 0 1]] 3))


(expect/expect "lohe" (encrypt "helo" "ba"))


(expect/expect "lohe" (encrypt-2 "helo" "ba"))

(expect/expect "helo" (decrypt "lohe" "ba"))
