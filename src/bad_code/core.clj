(ns bad-code.core)

(defn do-some-io
  "Pretend this fn downloads something from a Web server, or something."
  []
  (println (rand-int 100))
  (rand-int 100))

(defn acceptable?
  "This function is any kind of acceptance test for a value."
  [v]
  (= 36 (- v 7)))

(defn state-holder-1
  "We have some state, and we want to use it if it's good and update it if it's not.
  What's wrong, here?"
  [adam]
  (swap! adam
         (fn [content]
           (if (acceptable? content)
             content
             (do-some-io)))))

(defn state-holder-2
  "We have some state, and we want to use it if it's good and update it if it's not.
  What's wrong, here?"
  [adam]
  (if (and @adam (acceptable? @adam))
    @adam
    (reset! adam (do-some-io))))
