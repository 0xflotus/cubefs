Cluster
=======

Overview
--------

.. code-block:: bash

   curl -v "http://127.0.0.1/admin/getCluster" | python -m json.tool


display the base information of the cluster, such as the detail of metaNode,dataNode,vol and so on.

response

.. code-block:: json

   {
       "Name": "test",
       "LeaderAddr": "127.0.0.1:80",
       "DisableAutoAlloc": false,
       "Applied": 225,
       "MaxDataPartitionID": 100,
       "MaxMetaNodeID": 3,
       "MaxMetaPartitionID": 1,
       "DataNodeStat": {},
       "MetaNodeStat": {},
       "VolStat": {},
       "MetaNodes": {},
       "DataNodes": {}
   }


Freeze
------

.. code-block:: bash

   curl -v "http://127.0.0.1/cluster/freeze?enable=true"

if cluster is freezed,the vol never allocates dataPartitions automatically

.. csv-table:: Parameters
   :header: "Parameter", "Type", "Description"

   "enable", "bool", "if enable is true,the cluster is freezed"