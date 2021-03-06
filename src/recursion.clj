(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (*
      (first coll)
      (product (rest coll)))))

(defn singleton? [coll]
  (and
    (not (empty? coll))
    (empty? (rest coll))
    ))

(defn my-last [coll]
  (if (empty? coll)
    nil
    (if (empty? (rest coll))
      (first coll)
      (my-last (rest coll))
      )))

(defn max-element [a-seq]
  (if (empty? a-seq)
    nil
    (if (empty? (rest a-seq))
      (first a-seq)
      (max-element (cons
                   (max (first a-seq) (first (rest a-seq)))
                   (rest (rest a-seq)))
    ))))

(defn seq-max [seq-1 seq-2]
  (if (> (count seq-1) (count seq-2))
    seq-1
    seq-2))

(defn longest-sequence [a-seq]
  (if (empty? a-seq)
    nil
    (if (empty? (rest a-seq))
      (first a-seq)
      (longest-sequence (cons
                   (seq-max (first a-seq) (first (rest a-seq)))
                   (rest (rest a-seq)))
    ))))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
    a-seq
    (if (pred? (first a-seq))
      (cons (first a-seq) (my-filter pred? (rest a-seq)))
      (my-filter pred? (rest a-seq))
    )))

(defn sequence-contains? [elem a-seq]
  (cond
    (empty? a-seq) false
    (= elem (first a-seq)) true
    :else (sequence-contains? elem (rest a-seq))
    ))

(defn my-take-while [pred? a-seq]
  (cond
    (empty? a-seq) a-seq
    (pred? (first a-seq)) (cons (first a-seq) (my-take-while pred? (rest a-seq)))
    :else '()
    ))

(defn my-drop-while [pred? a-seq]
  (cond
    (empty? a-seq) a-seq
    (pred? (first a-seq)) (my-drop-while pred? (rest a-seq))
    :else a-seq
    ))


(defn seq= [a-seq b-seq]
  (cond
    (and (empty? a-seq) (empty? b-seq)) true
    (not (= (count a-seq) (count b-seq))) false
    (= (first a-seq) (first b-seq)) (seq= (rest a-seq) (rest b-seq))
    :else false
    ))

(defn my-map [f seq-1 seq-2]
  (if (or (empty? seq-1) (empty? seq-2))
    '()
    (cons
      (f (first seq-1) (first seq-2))
      (my-map f (rest seq-1) (rest seq-2)))
    )
  )

(defn power [n k]
  (if (zero? k)
    1
    (* n (power n (dec k)))
    ))

(defn fib [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ (fib (- n 1)) (fib (- n 2)))
    ))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< 0 how-many-times)
    (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))
    '()
    ))

(defn my-range [up-to]
  (if (< 0 up-to)
    (cons (dec up-to) (my-range (dec up-to)))
    '()
    ))

(defn tails [a-seq]
  (if (empty? a-seq)
    '(())
    (cons a-seq (tails (rest a-seq))
    )))

(defn inits [a-seq]
  (map reverse (tails (reverse a-seq)))
  )

(defn rotations [a-seq]
  (map
    #(vec (concat (take-last (- (count a-seq) %) a-seq) (take % a-seq)))
    (my-range (count a-seq))
    ))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
    freqs
    (let [k (first a-seq)
          new-value (inc (freqs k 0))
          new-freqs (assoc freqs k new-value)]
        (my-frequencies-helper new-freqs (rest a-seq))
      )))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies-helper [a-map a-seq]
  (if (empty? a-map)
    a-seq
    (let [pair (first a-map)
          k (first pair)
          v (second pair)
          subseq (repeat v k)
          new-seq (concat a-seq subseq)]
      (un-frequencies-helper (rest a-map) new-seq))
    ))

(defn un-frequencies [a-map]
    (un-frequencies-helper a-map '())
  )

(defn my-take-helper [n coll]
  (if (> 1 n)
    nil
    (cons (first coll) (my-take-helper (dec n) (rest coll)))
    ))

(defn my-take [n coll]
  (my-take-helper (min n (count coll)) coll))

(defn my-drop-helper [n coll]
  (if (> 1 n)
    coll
    (my-drop-helper (dec n) (rest coll))
    ))

(defn my-drop [n coll]
   (my-drop-helper (min n (count coll)) coll))

(defn halve [a-seq]
  (let [n (int (/ (count a-seq) 2))
        a (my-take n a-seq)
        b (my-drop n a-seq)]
      [(if (nil? a) '() a) b]
    ))

(defn seq-merge [a-seq b-seq]
  (cond
    (empty? a-seq) b-seq
    (empty? b-seq) a-seq
    :else (let [a (first a-seq)
                b (first b-seq)]
            (if (< a b)
              (cons a (seq-merge (rest a-seq) b-seq))
              (cons b (seq-merge a-seq (rest b-seq))))
    )))


(defn merge-sort [a-seq]
  ;If the sequence is 0 or 1 elements long, it is already sorted.
  (if (> 2 (count a-seq))
    a-seq
    ;Otherwise, divide the sequence into two subsequences.
    (let [halves (halve a-seq)
          left (first halves)
          right (second halves)
          ;Sort each subsequence recursively.
          sorted-left (merge-sort left)
          sorted-right (merge-sort right)
          ]
      ;Merge the two subsequences back into one sorted sequence.
      (seq-merge sorted-left sorted-right)
    )
  ))


(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

