(ns webhook.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [webhook.core-test]))

(doo-tests 'webhook.core-test)

