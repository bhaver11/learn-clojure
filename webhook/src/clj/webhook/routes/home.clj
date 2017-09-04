(ns webhook.routes.home
  (:require [webhook.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [clojure.data.json :as json]))

(def bitrix24-server "https://b24-n59aa51c26cc5d.bitrix24.com/rest/1/fg0mij0ubtk812ha/crm.contact.get")

(defn get-contact-details [id]
  (let [response (client/get bitrix24-server {:query-params {:id id}})]
    (clojure.pprint/pprint ((json/read-json (:body  response)) :result))))

(defn collect [data]
  (println "Response received")
  (let [id  (get-in data [:FIELDS :ID])]
      (get-contact-details id)))



(defn home-page []
  (layout/render "home.html"))

(defroutes home-routes
  (GET "/" []
       (home-page))
  (GET "/docs" []
       (-> (response/ok (-> "docs/docs.md" io/resource slurp))
           (response/header "Content-Type" "text/plain; charset=utf-8")))
  (POST "/contact" [data]
        (collect data))
  (POST "/update" [data]
        (collect data)))
