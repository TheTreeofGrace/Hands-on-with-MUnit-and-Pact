@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  pact-jvm-server startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and PACT_JVM_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\pact-jvm-server-4.1.42.jar;%APP_HOME%\lib\consumer-4.1.42.jar;%APP_HOME%\lib\matchers-4.1.42.jar;%APP_HOME%\lib\model-4.1.42.jar;%APP_HOME%\lib\pactbroker-4.1.42.jar;%APP_HOME%\lib\support-4.1.42.jar;%APP_HOME%\lib\ktor-server-netty-1.3.1.jar;%APP_HOME%\lib\ktor-server-host-common-1.3.1.jar;%APP_HOME%\lib\ktor-server-core-1.3.1.jar;%APP_HOME%\lib\kotlin-reflect-1.3.72.jar;%APP_HOME%\lib\ktor-network-tls-certificates-1.3.1.jar;%APP_HOME%\lib\ktor-network-tls-1.3.1.jar;%APP_HOME%\lib\ktor-http-cio-jvm-1.3.1.jar;%APP_HOME%\lib\ktor-http-jvm-1.3.1.jar;%APP_HOME%\lib\mordant-1.2.1.jar;%APP_HOME%\lib\ktor-network-1.3.1.jar;%APP_HOME%\lib\ktor-utils-jvm-1.3.1.jar;%APP_HOME%\lib\ktor-io-jvm-1.3.1.jar;%APP_HOME%\lib\atomicfu-0.14.1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.3.72.jar;%APP_HOME%\lib\kotlin-result-1.1.6.jar;%APP_HOME%\lib\colormath-1.2.0.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.3.72.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.72.jar;%APP_HOME%\lib\logback-classic-1.2.5.jar;%APP_HOME%\lib\logback-core-1.2.5.jar;%APP_HOME%\lib\scopt_2.12-3.5.0.jar;%APP_HOME%\lib\scala-logging_2.12-3.7.2.jar;%APP_HOME%\lib\unfiltered-netty-server_2.12-0.9.1.jar;%APP_HOME%\lib\kotlinx-coroutines-jdk8-1.3.3.jar;%APP_HOME%\lib\kotlinx-coroutines-core-1.3.3.jar;%APP_HOME%\lib\kotlinx-coroutines-core-common-1.3.3.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.72.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\diffutils-1.3.0.jar;%APP_HOME%\lib\generex-1.0.2.jar;%APP_HOME%\lib\automaton-1.11-8.jar;%APP_HOME%\lib\httpmime-4.5.14.jar;%APP_HOME%\lib\fluent-hc-4.5.14.jar;%APP_HOME%\lib\httpclient-4.5.14.jar;%APP_HOME%\lib\json-20220924.jar;%APP_HOME%\lib\unfiltered-netty_2.12-0.9.1.jar;%APP_HOME%\lib\netty-codec-http2-4.1.44.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.44.Final.jar;%APP_HOME%\lib\netty-handler-4.1.66.Final.jar;%APP_HOME%\lib\tika-core-1.28.5.jar;%APP_HOME%\lib\slf4j-api-1.7.36.jar;%APP_HOME%\lib\unfiltered_2.12-0.9.1.jar;%APP_HOME%\lib\unfiltered-util_2.12-0.9.1.jar;%APP_HOME%\lib\scala-xml_2.12-1.0.6.jar;%APP_HOME%\lib\scala-library-2.12.1.jar;%APP_HOME%\lib\commons-io-2.11.0.jar;%APP_HOME%\lib\java-semver-0.9.0.jar;%APP_HOME%\lib\commons-collections4-4.4.jar;%APP_HOME%\lib\mail-1.5.0-b01.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\java-diff-utils-4.12.jar;%APP_HOME%\lib\xercesImpl-2.12.0.jar;%APP_HOME%\lib\evo-inflector-1.2.2.jar;%APP_HOME%\lib\httpcore-4.4.16.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\netty-codec-4.1.66.Final.jar;%APP_HOME%\lib\netty-transport-native-kqueue-4.1.44.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.44.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.44.Final.jar;%APP_HOME%\lib\netty-transport-4.1.66.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.66.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.66.Final.jar;%APP_HOME%\lib\netty-common-4.1.66.Final.jar;%APP_HOME%\lib\alpn-api-1.1.3.v20160715.jar;%APP_HOME%\lib\antlr4-4.7.2.jar;%APP_HOME%\lib\kotlin-logging-1.12.5.jar;%APP_HOME%\lib\guava-30.1.1-jre.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\xml-apis-1.4.01.jar;%APP_HOME%\lib\antlr4-runtime-4.7.2.jar;%APP_HOME%\lib\ST4-4.1.jar;%APP_HOME%\lib\antlr-runtime-3.5.2.jar;%APP_HOME%\lib\org.abego.treelayout.core-1.0.3.jar;%APP_HOME%\lib\javax.json-1.0.4.jar;%APP_HOME%\lib\icu4j-61.1.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-qual-3.8.0.jar;%APP_HOME%\lib\error_prone_annotations-2.5.1.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\config-1.3.1.jar


@rem Execute pact-jvm-server
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %PACT_JVM_SERVER_OPTS%  -classpath "%CLASSPATH%" au.com.dius.pact.server.Server %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable PACT_JVM_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%PACT_JVM_SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
