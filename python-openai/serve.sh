#!/bin/bash

source .dev/bin/activate
uvicorn main:app --reload

