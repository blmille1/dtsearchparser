package com.millersoft.solr.search;

import java.io.IOException;
import java.util.Objects;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Query;

public class ScoreFilterQuery extends Query {
	private final Query query;
	private final Float minimumScore;
	private final Float maximumScore;
	
	public ScoreFilterQuery(Query query, float minScore, float maxScore) {
		this.query = Objects.requireNonNull(query, "Query must not be null");
		this.minimumScore = new Float(minScore);
		this.maximumScore = new Float(maxScore);
	}
	
	@Override
	public String toString(String field) {
		return new StringBuilder("ScoreFilterQuery(")
			      .append(query.toString(field))
			      .append(", ")
			      .append(minimumScore)
			      .append(", ")
			      .append(maximumScore)
			      .append(')')
			      .toString();
	}

	@Override
	public boolean equals(Object other) {
		ScoreFilterQuery sfq = ((ScoreFilterQuery) other);
		
		return sameClassAs(other)
				&& query.equals(sfq.query)
				&& this.minimumScore.equals(sfq.minimumScore)
				&& this.maximumScore.equals(sfq.maximumScore);
	}

	@Override
	public int hashCode() {
		return 31 * classHash() + query.hashCode() + minimumScore.hashCode() + maximumScore.hashCode();
	}

	public Query rewrite(IndexReader reader) throws IOException {
	    Query rewritten = query.rewrite(reader);

	    if (rewritten != query) {
	      return new ScoreFilterQuery(rewritten, minimumScore.floatValue(), maximumScore.floatValue());
	    }

	    return super.rewrite(reader);
	}
	/*
	 @Override
	  public Weight createWeight(IndexSearcher searcher, boolean needsScores) throws IOException {
	    final Weight innerWeight = searcher.createWeight(query, false);
	    if (needsScores) {
	      return new ConstantScoreWeight(this) {

	        @Override
	        public BulkScorer bulkScorer(LeafReaderContext context) throws IOException {
	          final BulkScorer innerScorer = innerWeight.bulkScorer(context);
	          if (innerScorer == null) {
	            return null;
	          }
	          return new ConstantBulkScorer(innerScorer, this, score());
	        }

	        @Override
	        public Scorer scorer(LeafReaderContext context) throws IOException {
	          final Scorer innerScorer = innerWeight.scorer(context);
	          if (innerScorer == null) {
	            return null;
	          }
	          final float score = score();
	          return new FilterScorer(innerScorer) {
	            @Override
	            public float score() throws IOException {
	              return score;
	            }
	            @Override
	            public int freq() throws IOException {
	              return 1;
	            }
	            @Override
	            public Collection<ChildScorer> getChildren() {
	              return Collections.singleton(new ChildScorer(innerScorer, "constant"));
	            }
	          };
	        }

	      };
	    } else {
	      return innerWeight;
	    }
	  }
	  */
}
