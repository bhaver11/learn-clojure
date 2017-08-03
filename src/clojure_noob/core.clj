(ns clojure-noob.core

  (:gen-class))
#_(use 'clojure.contrib.math)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(println "Hello")
(defn train
  []
  (println "something"))

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity "mild")
         "MILDLY INCONVENIENCED!"
         "DOOOOOOMED")))
(println '(1 2 3 4 5 6))
(hash-map "String" +)
(println (hash-map "string" +))
(reduce + 0 '(1 2 3 4 5))

(defn isodd
  [i]
  (if (zero? (mod i 2))
    false
    true))
(defn dec-maker
  [d]
  (fn [i] (- i d)))

(defn mapset
  [& i]
  (set (map inc  i)))

(def a '(1 2 3 4 5))



(defn f [i]
  (def a  (into [] i)))


(defn ff
  [arr]
  ((fn [arr my-arr]
      (if (empty? arr)
        my-arr
        (recur (rest arr) (cons (first arr) my-arr)))
      ) arr '())
  )
(ff [1 2 3 4])



#_(fn fib
  ([i]
   (fib i [1 1]))
  ([i my-arr]
   (if (< i 3)
     my-arr
     (cons (+ (fib (- i 1)) (fib (- i 2))) my-arr))) 4)



#_(fn fib
  [i my-arr]
  (if (< i 3)
    (cons 1 my-arr)
    (cons (+ (fib (- i 1) (fib (- i 2)))))))




(defn my-flatn
  ([i]
   (my-flatn i '()))
  ([i my-arr]
   (let [head (first i)
         tail (rest i)]
     (if (coll? head)
       (my-flatn head my-arr)
       (conj my-arr head))
     (if (coll? tail)
       (my-flatn tail my-arr)
       (conj my-arr tail))

     )))


;; core function implementation


(defn my-map
  ([f arr]
   (my-map f arr []))
  ([f arr my-arr]
   (if (empty? arr)
     my-arr
     (my-map f (rest arr) (conj my-arr (f (first arr)) )))))

(defn my-map2 [f arr]
  (reduce #(conj % (f %2)) [] arr))


(defn my-reduce
  ([f arr]
   (my-reduce f (f) arr))
  ([f acc arr]
   (if (empty? arr)
     acc
     (my-reduce f (f acc (first arr)) (rest arr)))))


(defn my-take
  ([n arr]
   (if (< (count arr) n)
     arr
     (my-take n arr [])))
  ([n arr my-arr]
   (if (<= n 0)
     my-arr
     (my-take (dec n) (rest arr) (conj my-arr (first arr))))))

(defn my-drop
  ([n arr]
   (my-drop n arr []))
  ([n arr my-arr]
   (if (empty? arr)
     my-arr
     (if-not (zero? n)
       (my-drop (dec n) (rest arr) my-arr)
       (my-drop 0 (rest arr) (conj my-arr (first arr)))))))

(defn my-take-while
  ([f arr]
   (my-take-while f arr []))
  ([f arr my-arr]
   (if (empty? arr)
     my-arr
     (if (f (first arr))
       (my-take-while f (rest arr) (conj my-arr (first arr)))
       my-arr))))

(defn my-filter
  ([f arr]
   (my-filter f arr []))
  ([f arr my-arr]
   (if (empty? arr)
     my-arr
     (if (f (first arr))
       (my-filter f (rest arr) (conj my-arr (first arr)))
       (my-filter f (rest arr) my-arr)))))

(my-filter odd? [1 2 3 4 5 6])


(defn my-some
  [f arr]
  (if (empty? arr)
    nil
    (if (f (first arr))
      true
      (my-some f (rest arr)))))

(my-some odd? [2 2 2])

;; sort implementation

(defn remove-element
  [arr el]
  (vec (concat (subvec arr 0 (.indexOf arr el)) (subvec arr (inc (.indexOf arr el))))))

(defn my-sort
  ([arr]
   (my-sort arr []))
  ([arr my-arr]
   (if (empty? arr)
     my-arr
     (my-sort (remove-element arr (apply min arr)) (conj my-arr (apply min arr))))))


(my-sort [11 2 2 3 4354 5 455 533 ])

;;conj implementation

(defn my-conj [target &  value]
   (into target (into [] value)))

(my-conj [ 2 3 4] [1])

;; into implementation

(defn my-into [target value]
  (apply conj target value))

(my-into [1 2 3 4] [1 2 3 4])



;; min implementation


(defn my-min-help [arr min]
  (if (empty? arr)
    min
    (recur (rest arr) (if (< (first arr) min)
                        (first arr)
                        min))))

(defn my-min
  ([& args]
   (my-min-help (rest args) (first args))))


;; concat implementation

(defn my-concat [arr1 arr2]
  (if (empty? arr2)
    arr1
    (recur (conj arr1 (first arr2)) (rest arr2))))

(defn cat-help [arr1 arr2]
  (if (empty? arr2)
    arr1
    (cat-help (my-concat arr1 (first arr2)) (rest arr2))))

(defn all-cat [arr1 & arr2]
  (println arr2)
  (if (empty? (first arr2))
    arr1
    (cat-help (my-concat arr1 (first arr2)) (rest arr2))))

(all-cat [1 2 3 4] [2 3 4] [3 4 5] [1])






;; complement function implementation

(defn my-comp [f]
  (fn [& args]
    (not (apply f args))))

(def not-empty? (my-comp  empty?))

(not-empty? [1 2 3 4])


;; sort-by implementation starts here

(defn h-map [arr mparr hmap]
  (if (empty? mparr)
    hmap
    (recur (rest arr) (rest mparr) (conj hmap (vector (first arr) (first mparr))))))




(defn min-by
  ([arr]
   (min-by (rest arr) (first arr)))
  ([arr min]
   (if (empty? arr)
     min
     (min-by (rest arr) (if (< (second (first arr)) (second min))
                          (first arr)
                          min)))))

(defn remove-by [arr min]
  (vec  (concat (subvec arr 0 (.indexOf arr min)) (subvec arr (inc (.indexOf arr min))))))




(defn f1 [arr my-arr]
  (if (empty? arr)
    my-arr
    (f1 (remove-by arr (min-by arr)) (conj my-arr (first (min-by arr))))))

(defn mysortby [f arr]
  (f1 (h-map arr (map f arr) []) [] ))

(mysortby count ["aaa" "bb" "c" "d"])

;; sort by implementation ends here



(defn repeat-list
  ([n arr]
   (repeat-list n 0 arr []))
  ([n m arr my-arr]
   (if (empty? arr)
     my-arr
     (if (zero? n)
       (repeat-list m n (rest arr) my-arr)
       (repeat-list (dec n) (inc m) arr (conj my-arr (first arr)))))))

(defn remove-odd
  ([arr]
   (remove-odd true arr []))
  ([flag arr my-arr ]
   (if (empty? arr)
     my-arr
     (if flag
       (remove-odd (not flag) (rest arr) my-arr)
       (remove-odd (not flag) (rest arr) (conj my-arr (first arr)))))))



(defn comp-help
  [func args]
  (if (empty? func)
    args
    (comp-help (rest func)  ((first func) args))))


(defn my-comp
  [& func]
  (fn composed
    ([& args]
     (comp-help (rest (reverse func)) (apply (first (reverse func)) args) ))
    ))

(def mult-int (my-comp inc *))

(def add-dec (my-comp dec +))

(def multyfn (my-comp inc dec int /))


;;problem 1, sum of multiples of 3 and 5

(reduce +  (filter #(or (= (mod % 3) 0) (= (mod % 5) 0)) (range 1 1000) ))


#_(defn fib
  ([i]
   (fib i [1 2]))
  ([i my-arr]
   (if (< (count my-arr) i)
     (fib i (conj my-arr (+ (last my-arr) (nth my-arr (- (count my-arr) 2)))))
     my-arr)))


;;problem 2
;; gives fibonacci numbers not greater than n
(defn fib-not-great
  ([n]
   (fib-not-great n [1 2]))
  ([n my-arr]
   (if (>= (+ (last my-arr) (nth my-arr (- (count my-arr) 2) ) ) n )
     my-arr
     (fib-not-great n (conj my-arr (+ (last my-arr) (nth my-arr (- (count my-arr) 2))))))))

;;sum of even fib numbers
(defn sum-fib-even
  [n]
  (reduce + (filter even? (fib-not-great n))))

;;problem 3 first try but did not work
(defn prime?
  ([num]
   (prime? num 2 false))
  ([num i flag]
   (if (or (= num 0) (= num 1) (> i (/ num 2)) flag)
     (not flag)
     (prime? num (inc i) (= (mod num i) 0)))))

#_(defn prime-list
  [num]
  (filter prime? (range 2 (inc (int (/ num 2))))))

(defn factor-list
  [num]
  (filter #(= 0 (mod num %)) (range 1 (inc (int (/ num 2))))))

(defn largest-prime-factor
  [num]
  (last (filter prime? (factor-list num))))



(defn encrypt-2
  "Without using recursion
  Returns cipher text when passed plain text and key"
  [plain key]
  (let [sort-key (sort key)
        plain-p (partition (count key)
                           plain)
        zmap (zipmap sort-key plain-p )]
    (apply str (map #(apply str
                            (get zmap %)) key))))


(defn decrypt
  "returns plain text when passed cipher and key"
  [cipher key]
  (let [sort-key (sort key)
        key-char (seq (char-arr key))
        cipher-p (partition (count key)
                            cipher)
        zmap (zipmap key-char cipher-p)]
    (apply str (map #(apply str
                            (get zmap %)) sort-key))))



(defn make-rand-arr
  [n]
  (let [limit (* n n)
        arr (range 0 (/ limit 2))]
    (shuffle (concat arr arr))))


(defn make-board
  ([n] (make-board {} (make-rand-arr n) 1))
  ([board rand-arr pos]
   (if (empty? rand-arr)
     board
     (make-board (assoc board pos (str (first rand-arr))) (rest rand-arr) (inc pos)))))


;;;;;;;;;;;;;;;;;;;;;; luhn algorithm



(defn num-to-list
  "Converts a string to a list of numbers, if the string has ? it is converted to -1"
  [num]
   (mapv (fn [^Character c] (Character/digit c 10)) (str num)))


(defn rev-interleave
  "reverse interleaves a sequence into number of sequences"
  [arr n]
  (let [part (partition n arr)]
    (apply map list part)))


(defn luhn-check
  "Applies the luhn algorithm , returns true if the sum is divisible by 10, false otherwise"
  [num]
  (let [[odd even] (rev-interleave num 2)
        odd-sum (map #(if (> 10 (* % 2))
                        (* % 2)
                        (- (* % 2) 9))
                     odd)]

    (= 0 (mod (+ (reduce + even)
                 (reduce + odd-sum))
              10))))



(defn missing-num
  "if -1 is present then it replaces it with digits 0-9 and then checks for luhn test, returns the first list which passes the test."
  [num-list]
  (letfn [(replace-by [y]
            (map (fn [x]
                   (if (= x -1)
                     y
                     x))
                 num-list))]
    (let [replaced-arr (map replace-by
                            (range 0 10))]
      #_(println replaced-arr)
      (some (fn [arr]
              (if (luhn-check arr)
                arr)) replaced-arr))))



(defn swap
  "swaps two given indexes in a vector"
  [arr i1 i2]
  (assoc arr i2 (arr i1) i1 (arr i2)))


(defn swap-error
  "swaps adjacent digits, one pair at a time then checks for luhn test, if true returns the list , else swaps next to digits and repeats."
  [num-arr n]
  (let [swapped-arr (map (fn [x y]
                           (swap num-arr
                                 x
                                 y))
                         (range 0 (- n 1)) (range 1 n))]
    (some (fn [arr]
            (if (luhn-check arr)
              arr)) swapped-arr)))


(defn luhn-main
  "Gets debit card number as a string.
  If it has a missing digit, replaces that digit with a valid digit
  If not it checks if two numbers are swapped and returns the valid number after deswapping them. "
  [num-string]
  (let [num-arr (num-to-list num-string)]
    (println num-arr)
    (if (some neg? num-arr)
      (missing-num num-arr)
      (swap-error num-arr 16))))




(defn make-board
  ([] (make-board {} 1))
  ([bmap index]
   (if (= index 10)
     (assoc bmap :next-move "x")
     (recur (assoc bmap index "") (inc index)))))

(def board (atom (make-board)))



(def board (atom {0 {0 {:value ""
                                :disabled false}
                             1 {:value "x"
                                :disabled false}
                             2 {:value ""
                                :disabled false}}
                          1 {0 {:value ""
                                :disabled false}
                             1 {:value ""
                                :disabled false}
                             2 {:value ""
                                :disabled false}}
                          2 {0 {:value ""
                                :disabled false}
                             1 {:value ""
                                :disabled false}
                             2 {:value ""
                                :disabled false}}
                          :next-mov 0}))


(defn get-rows
  []
  (for [x (range 3)
        y (range 3)

        :let [val  (get-in @board [x y :value])]]
    val))


;;;;;; TRAVERSE A MATRIX SPIRALLY

(def status (atom {:direction :right
                   :cur-pos {:x -1
                             :y 0}
                   :count 0}))


(defn arr-maker
  "Returns an array with positions where the direction needs to be changed"
  ([size] (arr-maker (dec size) [size] 1))
  ([size arr toggle]
   (if (zero? size)
     arr
     (if (zero? toggle)
       (recur (dec size) (conj arr (+ (last arr) size)) 1)
       (recur size (conj arr (+ (last arr) size)) 0)))))

(defn arr-maker-2
  "Returns count on which direction needs to be changed
  for two player spiral game.
  Almost similar to arr-maker excpet that it decrements the
  size by 2 instead of 1"
  ([size] (arr-maker-2 (- size 2) [size] 1))
  ([size arr toggle]
   (if (zero? size)
     arr
     (if (zero? toggle)
       (recur (- size 2) (conj arr (+ (last arr) size)) 1)
       (recur size (conj arr (+ (last arr) size)) 0)))))

(def change-on (atom (arr-maker-2 6)))

(defn change-direction
  "changes the direction to next-dir"
  [next-dir]
  (swap! status assoc :direction next-dir))



(defn upd-gen
  "updates the postion i.e. x or y co-ordinate depending upon current direction"
  [count pos next-dir f]
  (swap! status update-in [:cur-pos pos] f)
  (swap! status update :count inc)
  (if (some #(= % (get @status :count)) @change-on)
    (change-direction  next-dir)))


(defn upd-pos
  "Main function which will be called when button is clicked
  will update the current position in the status atom"
  []
  (let [direction (get @status :direction)
        count (get @status :count)]
    (cond
      (= direction :right) (upd-gen count :x :down inc)
      (= direction :down)  (upd-gen count :y :left inc)
      (= direction :left)  (upd-gen count :x :up dec)
      (= direction :up)    (upd-gen count :y :right dec))))



(defn test-func
  "Test function to test for a board of size 6
  calls upd-pos untill all postions are done."
  [board]
  (if-not (= 18 (:count @status))
    (do
      (upd-pos)
      (print board)
      (recur (conj board (vector (get-in @status [:cur-pos :x])
                           (get-in @status [:cur-pos :y])))))
    board))
