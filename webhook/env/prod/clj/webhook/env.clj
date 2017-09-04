(ns webhook.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[webhook started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[webhook has shut down successfully]=-"))
   :middleware identity})
