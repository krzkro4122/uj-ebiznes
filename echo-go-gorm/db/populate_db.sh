#!/bin/bash
cat db/populate_db.sql | sqlite3 db/db.db
