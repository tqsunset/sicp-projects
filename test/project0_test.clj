(ns project0-test
  (:require [clojure.test :refer :all]
            [sicp-projects.project0 :refer :all]))

(deftest bitfunc-test
  (testing "Problem 1: Bitdiddle's function"
    (is (== (bitfunc 0) 4))                                 ;; Should be 4
    (is (== (bitfunc 1) 0))                                 ;; Should be 0, or very close
    (is (== (bitfunc 2) 0))                                 ;; Should also be very close to 0
    (is (== (bitfunc -1) 0))                                ;; Should also also be very close to 0
    (is (pos? (bitfunc 10)))))                              ;; Should be pretty big, and positive)

(deftest bitfunc-rect-test
  (testing "Problem 2: A rectangle under Bitdiddle's function"
    (is (== 4 (bitfunc-rect 0 1)))                          ;; Should be 4
    (is (== 2 (bitfunc-rect 0 0.5)))                        ;; Should be 2
    (is (neg? (bitfunc-rect 1.5 2)))                        ;; Should be negative
    ))

(defn bitfunc-integral-helper [x]
  (let [fifth (exp x 5)
        third (exp x 3)]
    (+ (* 1/5 fifth)
       (* -5/3 third)
       (* 4 x))))

(defn bitfunc-integral [x1 x2]
  (->> [x2 x1]
       (mapv bitfunc-integral-helper)
       (apply -)
       double))

(deftest bitfunc-integral-test
  (testing "Problem 3: Integrating Bitdiddle's function"
    (is (> 0.001 (abs (- (bitfunc-integral-recur 3000 0 1) (bitfunc-integral-recur 3000 -1 0)))))
    (is (> 0.001 (abs (- (bitfunc-integral-iter 3000 0 1) (bitfunc-integral-iter 3000 -1 0)))))
    (is (> 0.001 (abs (- (bitfunc-integral-recur 2000 0 1) (bitfunc-integral 0 1)))))
    (is (> 0.001 (abs (- (bitfunc-integral-iter 2000 0 1) (bitfunc-integral 0 1)))))))

;; TODO : iteration 방식의 오류 수정
(run-tests)
