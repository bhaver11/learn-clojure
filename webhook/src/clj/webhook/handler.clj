(ns webhook.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [webhook.layout :refer [error-page]]
            [webhook.routes.home :refer [home-routes]]
            [compojure.route :as route]
            [webhook.env :refer [defaults]]
            [mount.core :as mount]
            [webhook.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    (-> #'home-routes
        #_(wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
