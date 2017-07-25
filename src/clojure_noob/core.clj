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
                            (get zmap #)) sort-key))))
