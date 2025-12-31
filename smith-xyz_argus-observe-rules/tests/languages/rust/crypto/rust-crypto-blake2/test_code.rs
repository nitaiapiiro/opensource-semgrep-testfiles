use blake2::{Blake2b, Blake2s, Blake2b512, Blake2b256, Blake2s256, Blake2s128, Digest};

fn blake2_patterns() {
    let data = b"test data";
    let key = b"key";
    let salt = b"salt";
    let personal = b"personal";

    // Pattern: blake2::Blake2b::new($KEY) - Line 9
    let mut hasher1 = Blake2b::new(key);

    // Pattern: blake2::Blake2s::new($KEY) - Line 12
    let mut hasher2 = Blake2s::new(key);

    // Pattern: blake2::Blake2b::new_with_params($KEY, $SALT, $PERSONAL) - Line 15
    let mut hasher3 = Blake2b::new_with_params(key, salt, personal);

    // Pattern: blake2::Blake2s::new_with_params($KEY, $SALT, $PERSONAL) - Line 18
    let mut hasher4 = Blake2s::new_with_params(key, salt, personal);

    // Pattern: let mut $HASHER = blake2::Blake2b::new($KEY); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 21
    let mut hasher5 = Blake2b::new(key);
    hasher5.update(data);
    let hash5 = hasher5.finalize();

    // Pattern: use blake2::{Blake2b, Digest}; let mut $HASHER = Blake2b::new($KEY); $HASHER.update($DATA); $HASHER.finalize() - Line 26
    let mut hasher6 = Blake2b::new(key);
    hasher6.update(data);
    let _hash6 = hasher6.finalize();

    // Pattern: blake2::Blake2b512::new() - Line 30
    let mut hasher7 = Blake2b512::new();

    // Pattern: blake2::Blake2b256::new() - Line 33
    let mut hasher8 = Blake2b256::new();

    // Pattern: blake2::Blake2s256::new() - Line 36
    let mut hasher9 = Blake2s256::new();

    // Pattern: blake2::Blake2s128::new() - Line 39
    let mut hasher10 = Blake2s128::new();

    // Pattern: let mut $HASHER = blake2::Blake2b512::new(); $HASHER.update($DATA); $HASHER.finalize() - Line 42
    let mut hasher11 = Blake2b512::new();
    hasher11.update(data);
    let _hash11 = hasher11.finalize();

    // Pattern: let mut $HASHER = blake2::Blake2s256::new(); $HASHER.update($DATA); $HASHER.finalize_into($OUTPUT) - Line 47
    let mut hasher12 = Blake2s256::new();
    hasher12.update(data);
    let mut output = [0u8; 32];
    hasher12.finalize_into((&mut output).into());
}
