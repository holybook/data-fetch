data-fetch
==========

Used to convert data from different sources into a common well defined format.

### reference.bahai.org

The implementation to fetch data from the Bahá'í reference library can be found in `ws.holybook.documents.referencelibrary.CrawlReferenceLibarary`. It can be invoked from the command line by navigating to the `data-fetch` directory and executing:

```
$ ./fetch_reference_library.sh [<path-to-data-directory> [<url-to-single-book>]]
```
Note that in order for this script to work, the `data-model` project must be in the same folder as the `data-fetch` project. Both arguments are optional:

- `path-to-data-directory`: A path where the output documents are to be stored. Default is `.`.
- `url-to-single-book`: A url pointing to a single book in the reference library. If this is not provided all books are fetched.
