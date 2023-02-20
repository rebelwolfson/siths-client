(defproject app "0.1.0"
  :plugins [[lein-tailwind "0.1.2"]
            [lein-figwheel "0.5.18"]
            [lein-cljsbuild "1.1.8" :exclusions [[org.clojure/clojure]]]]

  :tailwind {:tailwind-dir "src"
             :output-dir   "out"
             :styles [{:src "resources/public/index.css"
                       :dst "out/index.css"}]}
  :figwheel {:http-server-root "public"
             :server-port 3449
             :server-ip   "0.0.0.0"
             :css-dirs ["resources/public"]}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.11.60"]
                 [org.clojure/core.async "1.6.673"]
                 [quiescent "0.3.2"]
                 [org.clojars.franks42/cljs-uuid-utils "0.1.3"]
                 [ring "1.9.6"]]

  :ring {:handler app.serve/handler}
  :source-paths ["src"]
  :resource-paths ["resources"]

  :cljsbuild {:builds {:dev {:id "dev"
                             :source-paths ["src"]
                             :figwheel {:on-jsload "app.core/on-js-reload"
                                        :open-urls ["http://localhost:3449/index.html"]}
                             :compiler {:output-dir "out/dev"
                                        :output-to "out/dev/index.js"
                                        :optimizations :none
                                        :source-map true}}
                       :prod {:id "prod"
                              :source-paths ["src"]
                              :compiler {:output-dir "out/prod"
                                         :output-to "out/prod/index.js"
                                         :optimizations :advanced
                                         :preamble ["react-0.10.0.min.js"]
                                         :externs ["react-externs-0.10.0.js"]
                                         :pretty-print false
                                         :closure-warnings {:non-standard-jsdoc :off}}}}})

