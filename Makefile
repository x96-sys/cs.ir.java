# Diretórios
BUILD_DIR     = build
MAIN_BUILD    = $(BUILD_DIR)/main
CLI_BUILD     = $(BUILD_DIR)/cli
TEST_BUILD    = $(BUILD_DIR)/test
COVERAGE_DIR  = $(BUILD_DIR)/coverage
SRC_DIRS      = src/main src/cli src/test

LIB_DIR       = lib
TOOL_DIR      = tools

# Dependências
FLUX_VERSION       = 1.0.0
FLUX_JAR           = $(LIB_DIR)/org.x96.sys.foundation.io.jar
FLUX_URL           = https://github.com/x96-sys/flux.java/releases/download/v$(FLUX_VERSION)/org.x96.sys.foundation.io.jar

TOKENIZER_VERSION  = 0.1.4
TOKENIZER_JAR      = $(LIB_DIR)/org.x96.sys.foundation.tokenizer.jar
TOKENIZER_URL      = https://github.com/x96-sys/tokenizer.java/releases/download/$(TOKENIZER_VERSION)/org.x96.sys.foundation.tokenizer.jar

JUNIT_VERSION = 1.13.4
JUNIT_JAR     = $(TOOL_DIR)/junit-platform-console-standalone.jar
JUNIT_URL     = https://maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$(JUNIT_VERSION)/junit-platform-console-standalone-$(JUNIT_VERSION).jar

GJF_VERSION   = 1.28.0
GJF_JAR       = $(TOOL_DIR)/google-java-format.jar
GJF_URL       = https://maven.org/maven2/com/google/googlejavaformat/google-java-format/$(GJF_VERSION)/google-java-format-$(GJF_VERSION)-all-deps.jar

JACOCO_VERSION   = 0.8.12
JACOCO_JAR       = $(TOOL_DIR)/jacoco-agent.jar
JACOCO_CLI       = $(TOOL_DIR)/jacoco-cli.jar
JACOCO_AGENT_URL = https://repo1.maven.org/maven2/org/jacoco/org.jacoco.agent/$(JACOCO_VERSION)/org.jacoco.agent-$(JACOCO_VERSION)-runtime.jar
JACOCO_CLI_URL   = https://repo1.maven.org/maven2/org/jacoco/org.jacoco.cli/$(JACOCO_VERSION)/org.jacoco.cli-$(JACOCO_VERSION)-nodeps.jar

# Classpaths
CP_MAIN  = $(FLUX_JAR):$(TOKENIZER_JAR)
CP_TEST  = $(MAIN_BUILD):$(CP_MAIN):$(JUNIT_JAR)
CP_CLI   = $(MAIN_BUILD):$(CP_MAIN)

# Fontes
JAVA_SOURCES = $(shell find $(SRC_DIRS) -name "*.java")

# Artefato distribuível
DISTRO_JAR=org.x96.sys.foundation.cs.ir.jar

# Alvos principais
all: clean build-main build-test coverage-report

# Builds
build-main:
	@mkdir -p $(MAIN_BUILD)
	@javac -d $(MAIN_BUILD) -cp $(CP_MAIN) $(shell find src/main -name "*.java")

build-cli: build-main
	@mkdir -p $(CLI_BUILD)
	@javac -d $(CLI_BUILD) -cp $(CP_CLI) $(shell find src/cli -name "*.java" 2>/dev/null || true)

build-test: tools/junit | $(TEST_BUILD)
	@javac -d $(TEST_BUILD) -cp $(CP_TEST) $(shell find src/test -name "*.java")

# Testes
test: build-test
	@java -jar $(JUNIT_JAR) execute \
	   --class-path $(TEST_BUILD):$(MAIN_BUILD):$(CLI_BUILD):$(CP_MAIN) \
	   --scan-class-path

# Cobertura
test-coverage: build-test tools/jacoco | $(COVERAGE_DIR)
	@echo "📊 Executando testes com cobertura..."
	@java -javaagent:$(JACOCO_JAR)=destfile=$(COVERAGE_DIR)/jacoco.exec,excludes=java.*:javax.*:sun.*:jdk.*:com.sun.*:org.junit.* \
	   -jar $(JUNIT_JAR) \
	   execute \
	   --class-path $(TEST_BUILD):$(MAIN_BUILD):$(CLI_BUILD):$(CP_MAIN) \
	   --scan-class-path

coverage-report: test-coverage
	@echo "📋 Gerando relatório de cobertura..."
	@java -jar $(JACOCO_CLI) report $(COVERAGE_DIR)/jacoco.exec \
	   --classfiles $(MAIN_BUILD) \
	   --sourcefiles src/main \
	   --html $(COVERAGE_DIR)/html \
	   --xml $(COVERAGE_DIR)/jacoco.xml \
	   --csv $(COVERAGE_DIR)/jacoco.csv
	@echo "✅ Relatório em $(COVERAGE_DIR)/html/index.html"

distro: lib
	jar cf $(DISTRO_JAR) -C $(MAIN_BUILD) .

# Downloads
lib:
	@mkdir -p $(LIB_DIR)

lib/flux: lib
	@[ -f $(FLUX_JAR) ] || (echo "📦 Baixando FLUX..."; curl -L -o $(FLUX_JAR) $(FLUX_URL))

lib/tokenizer: lib
	@[ -f $(TOKENIZER_JAR) ] || (echo "📦 Baixando TOKENIZER..."; curl -L -o $(TOKENIZER_JAR) $(TOKENIZER_URL))

tools:
	@mkdir -p $(TOOL_DIR)

tools/junit: tools
	@[ -f $(JUNIT_JAR) ] || (echo "📦 Baixando JUnit..."; curl -L -o $(JUNIT_JAR) $(JUNIT_URL))

tools/gjf: tools
	@[ -f $(GJF_JAR) ] || (echo "📦 Baixando Google Java Format..."; curl -L -o $(GJF_JAR) $(GJF_URL))

tools/jacoco: tools
	@[ -f $(JACOCO_JAR) ] || (echo "📦 Baixando JaCoCo Agent..."; curl -L -o $(JACOCO_JAR) $(JACOCO_AGENT_URL))
	@[ -f $(JACOCO_CLI) ] || (echo "📦 Baixando JaCoCo CLI..."; curl -L -o $(JACOCO_CLI) $(JACOCO_CLI_URL))

# Formatação
format: tools/gjf
	@find src -name "*.java" -print0 | xargs -0 java -jar $(GJF_JAR) --aosp --replace

# Limpeza
clean:
	@rm -rf $(BUILD_DIR)

$(TEST_BUILD) $(COVERAGE_DIR):
	@mkdir -p $@
