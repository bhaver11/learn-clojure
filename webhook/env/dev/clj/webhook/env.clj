(ns webhook.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [webhook.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[webhook started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[webhook has shut down successfully]=-"))
   :middleware wrap-dev})
