(defproject sicpclojure "0.1.0-SNAPSHOT"
  :description "A static site generator for SICP in Clojure."
  :url "http://github.com/ecmendenhall"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.pegdown/pegdown "1.2.0"]
                 [hickory "0.2.3"]
                 [hiccup "1.0.2"]
                 [fs "1.3.2"]
                 [watchtower "0.1.1"]
                 [compojure "1.1.5"]
                 [ring/ring-core "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]])
