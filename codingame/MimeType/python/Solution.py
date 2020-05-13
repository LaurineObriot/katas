import sys

nbAssociations, nbFiles = int(input()), int(input())

# assoc associates to each file extension the corresponding MIME type
assoc = {}
for i in range(nbAssociations):
    ext, mime = input().split()
    assoc[ext.lower()] = mime

for i in range(nbFiles):
    name = input().lower().split('.')
    ext = name[-1] if len(name) != 1 else None
    print(assoc[ext] if ext in assoc else "UNKNOWN")
