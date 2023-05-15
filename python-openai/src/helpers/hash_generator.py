
import bcrypt


def hash_password(password: bytes) -> tuple[bytes, bytes]:
    salt = bcrypt.gensalt()
    hashed = bcrypt.hashpw(password, salt)
    return hashed, salt


def example_usage():
    password = b'ebiznesPassword'
    hash, salt = hash_password(password)
    print(hash, salt)


if __name__ == "__main__":
    example_usage()
