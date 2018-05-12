import argparse

from google.cloud import storage

def remove_bucket_iam_member(project_name, bucket_name):
    # setting storage client
    storage_client = storage.Client(project=project_name)
    
    # setting bucket
    bucket = storage_client.bucket(bucket_name)
    
    # setting bucket policy
    policy = bucket.get_iam_policy()
    
    # discarding Storage Legacy Bucket Owner Permissions
    policy['roles/storage.legacyBucketOwner'].discard('projectEditor:{}'.format(project_name.lower()))
    policy['roles/storage.legacyBucketOwner'].discard('projectOwner:{}'.format(project_name.lower()))
    bucket.set_iam_policy(policy)
    print('Removed Bucket Owner in the {} bucket'.format(bucket_name))
    
    # discarding Storage Legacy Bucket Reader Permissions

    # This code is commented on the original script you've provided.
    policy['roles/storage.legacyBucketReader'].discard('projectViewer:{}'.format(project_name.lower()))
    
    bucket.set_iam_policy(policy)
    print('Removed Bucket Reader in the {} bucket'.format(bucket_name))


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description=__doc__,
        formatter_class=argparse.RawDescriptionHelpFormatter)

    parser.add_argument('project_name', help='Your Project ID here')
    parser.add_argument('bucket_name', help='Your bucket name here')

    args = parser.parse_args()
    remove_bucket_iam_member(args.project_name, args.bucket_name)