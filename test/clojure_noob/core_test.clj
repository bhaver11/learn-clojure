(ns clojure-noob.core-test
  (:require #_[clojure.test :refer :all]
            [clojure-noob.core :refer :all]
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
