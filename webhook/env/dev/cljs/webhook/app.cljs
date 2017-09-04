(ns ^:figwheel-no-load webhook.app
  (:require [webhook.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
