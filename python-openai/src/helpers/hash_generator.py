
import bcrypt


def hash_password(password: str, salt = bcrypt.gensalt()) -> tuple[bytes, bytes]:
    hashed = bcrypt.hashpw(password.encode(), salt)
    return hashed.decode(), salt.decode()


def check_hash(password: str, password_hash: str):
    return bcrypt.checkpw(password.encode(), password_hash.encode())


def example_usage():
    password = 'ebiznesPassword'
    hash, salt = hash_password(password)
    print(hash, salt)


if __name__ == "__main__":
    example_usage()
