.PHONY: install watch build publish clean tests flake8

all: clean tests coverage flake8 build

venv: requirements.txt
	virtualenv -p python3 venv --no-site-packages
	./venv/bin/pip install -r requirements.txt
	touch venv

clean:
	rm -rf flake8 coverage .coverage tests.xml build dist 

flake8:
	flake8 src/

coverage: tests
	coverage html --directory=coverage

tests:
	pytest --cov=rtfm --junitxml=tests.xml src/

install:
	test -n "$(VIRTUAL_ENV)"
	python3 setup.py install
	#python3 setup.py install_data

watch:
	test -n "$(VIRTUAL_ENV)"
	rerun  -d src -p "*.py" -x "make install >/dev/null 2>&1"

build: tests
	python3 setup.py sdist bdist_wheel

publish: all
	twine upload --repository-url https://upload.pypi.org/legacy/ dist/*
