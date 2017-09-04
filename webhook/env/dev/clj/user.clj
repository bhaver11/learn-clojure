(ns user
  (:require [mount.core :as mount]
            [webhook.figwheel :refer [start-fw stop-fw cljs]]
            webhook.core))

(defn start []
  (mount/start-without #'webhook.core/repl-server))

(defn stop []
  (mount/stop-except #'webhook.core/repl-server))

(defn restart []
  (stop)
  (start))


