import random
def slowshuffle(N):
    shuffled = []
    isNotPresent = [True] * (N + 1)
    count = 0

    while count < N - 1:
        r = random.randint(1, N)

        if isNotPresent[r]:
            shuffled.append(r)
            isNotPresent[r] = False
            count += 1

    # find last remaining number
    for i in range(1, N + 1):
        if isNotPresent[i]:
            shuffled.append(i)
            break

    return shuffled


def shuffle(N):
    B = [i + 1 for i in range(N)]
    b = 0

    while b < N:
        r = random.randint(b, N - 1)
        B[b], B[r] = B[r], B[b]
        b += 1

    return B


def test_shuffle(method, N, trials):
    D = {}

    for _ in range(trials):
        result = method(N)
        key = ''.join(str(x) for x in result)

        if key not in D:
            D[key] = 1
        else:
            D[key] += 1

    for key in D:
        print(key, D[key])


print("Testing slowshuffle:")
test_shuffle(slowshuffle, 3, 60000)

print("\nTesting unbiased shuffle:")
test_shuffle(shuffle, 3, 60000)