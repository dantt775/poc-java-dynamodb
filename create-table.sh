aws --endpoint-url=http://172.28.5.10:4569 dynamodb create-table \
    --table-name proposal \
    --attribute-definitions AttributeName=solicitationId,AttributeType=N \
    --key-schema AttributeName=solicitationId,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \

