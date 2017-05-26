


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Tweet
{
	int _TimeStamp, _tweetID;

	public Tweet( int ts, int id )
	{
		_TimeStamp = ts;
		_tweetID = id;
	}
}

class TwitterIndividual
{
	int _userID;
	List<Tweet> _tweetList;
	List<Integer> _followList;

	public TwitterIndividual( int userID )
	{
		_userID = userID;
		_tweetList = new LinkedList<>();
		_followList = new ArrayList<>();
		_followList.add( userID );
	}

	public TwitterIndividual( int userID, int timeStamp, int tweet )
	{
		this( userID );

		_tweetList.add( new Tweet( timeStamp, tweet ) );

	}

	public void addFollow( int followeeID )
	{
		if ( !_followList.contains( followeeID ) )
			_followList.add( followeeID );
	}

	public void deleteFollow( int followeeID )
	{
		if ( _followList.contains( followeeID ) )
			_followList.remove( _followList.indexOf( followeeID ) );
	}

	public List<Integer> getFollowing()
	{
		return _followList;
	}

	public List<Tweet> getTweets()
	{
		return this._tweetList;
	}

}

public class Twitter
{
	List<TwitterIndividual> _twitterUser;
	List<Integer> _userList;
	int _timeStamp;

	/** Initialize your data structure here. */
	public Twitter()
	{
		_twitterUser = new ArrayList<>();
		_userList = new ArrayList<>();
	}

	/** Compose a new tweet. */
	public void postTweet( int userId, int tweetId )
	{
		if ( !_userList.contains( userId ) )
		{
			_userList.add( userId );
			_twitterUser.add( new TwitterIndividual( userId, _timeStamp++, tweetId ) );
		}
		else
		{
			List<Tweet> tweets = getUser( userId ).getTweets();
			if ( tweets.size() > 10 )
				tweets.remove( 0 );
			getUser( userId ).getTweets().add( new Tweet( _timeStamp++, tweetId ) );

		}

	}

	TwitterIndividual getUser( int userID )
	{
		if ( _userList.contains( userID ) )
			return _twitterUser.get( _userList.indexOf( userID ) );
		return null;
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by
	 * the user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed( int userId )
	{
		// List<List<Tweet>> newsList = new ArrayList<List<Tweet>>();
		List<Tweet> newsList = new ArrayList<>();
		List<Integer> newsListTweets = new ArrayList<>();
		if ( getUser( userId ) == null )
			return newsListTweets;
		List<Integer> followList = getUser( userId )._followList;
		for ( int i = 0; i < followList.size(); i++ )
			newsList.addAll( getUser( followList.get( i ) )._tweetList );
		Collections.sort( newsList, ( a, b ) -> ( b._TimeStamp - a._TimeStamp ) );

		if ( newsList.size() > 10 )
			newsList = newsList.subList( 0, 10 );

		for ( int i = 0; i < newsList.size(); i++ )
			newsListTweets.add( newsList.get( i )._tweetID );

		return newsListTweets;
	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow( int followerId, int followeeId )
	{

		if ( !_userList.contains( followerId ) )
		{
			_userList.add( followerId );
			_twitterUser.add( new TwitterIndividual( followerId ) );
		}
		if ( !_userList.contains( followeeId ) )
		{
			_userList.add( followeeId );
			_twitterUser.add( new TwitterIndividual( followeeId ) );
		}
		getUser( followerId ).addFollow( followeeId );

	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow( int followerId, int followeeId )
	{
		if ( followerId == followeeId )
			return;
		if ( _userList.contains( followerId ) )
			getUser( followerId ).deleteFollow( followeeId );
	}

	public static void main( String[] args )
	{
		Twitter twitter = new Twitter();
		twitter.postTweet( 1, 5 );
		twitter.unfollow( 1, 1 );
		twitter.getNewsFeed( 1 );
	}

}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */