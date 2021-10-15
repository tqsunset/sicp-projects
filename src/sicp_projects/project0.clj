(ns sicp-projects.project0)

(defn abs [x]
  (max x (- x)))

(defn exp [x n]
  (apply * (repeat n x)))

(defn bitfunc [x]
  (let [square (exp x 2)]
    (* (- square 4)
       (- square 1))))

(defn bitfunc-rect [x1 x2]
  (* (- x2 x1) (bitfunc x1)))

(defn bitfunc-integral-recur [num-steps x1 x2]
  (if (= num-steps 1)
    (bitfunc-rect x1 x2)
    (let [step (/ (- x2 x1) num-steps)
          p1 (+ x1 step)]
      (+ (bitfunc-rect x1 p1) (bitfunc-integral-recur (dec num-steps) p1 x2)))))

(defn bitfunc-integral-iter [num-steps x1 x2]
  (loop [p1 x1
         p2 x2
         num-steps num-steps
         area 0]
    (if (= num-steps 1)
      (+ area (bitfunc-rect p1 p2))
      (let [step (/ (- p2 p1) num-steps)
            ps (+ p1 step)]
        (recur ps 2 (dec num-steps) (+ area (bitfunc-rect p1 ps)))))))

(defn bitfunc-integral-difference [num-steps x1 x2]
  (-> (- (bitfunc-integral-iter num-steps x1 x2)
         (bitfunc-integral-recur num-steps x1 x2))
      abs))
