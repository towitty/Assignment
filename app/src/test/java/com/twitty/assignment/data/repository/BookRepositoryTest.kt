package com.twitty.assignment.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.twitty.assignment.data.source.network.model.NetworkBook
import com.twitty.assignment.data.source.network.retrofit.NetworkDataSource
import com.twitty.assignment.model.Book
import com.twitty.assignment.model.asEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class BookRepositoryTest {

    private lateinit var subject: BookRepository

    private lateinit var networkDataSource: NetworkDataSource
    private lateinit var bookDao: FakeBookDao

    @Before
    fun setUp() {
        networkDataSource = mock()
        bookDao = FakeBookDao()

        subject = BookRepositoryImpl(
            bookDao = bookDao, networkDataSource = networkDataSource
        )
    }

    @Test
    fun `searchBooks 제목으로 검색 하는 경우 검색한 제목이 포함된 책 반환`() = runTest {
        val title = "Android"

        // Given
        val networkBooks = listOf(
            NetworkBook(
                isbn = "1",
                title = "Android Developer",
                author = "kim young june",
                publisher = "google",
                publicationDate = "1996-03-29",
                description = "description",
                image = "https://example.com/image111.jpg",
                link = "https://example.com/link111",
                price = "11.11",
            ),
            NetworkBook(
                isbn = "2",
                title = "Kotlin",
                author = "kim young june",
                publisher = "google",
                publicationDate = "1996-03-29",
                description = "description",
                image = "https://example.com/image222.jpg",
                link = "https://example.com/link222",
                price = "22.22",
            ),
            NetworkBook(
                isbn = "3",
                title = "Android",
                author = "kim young june",
                publisher = "google",
                publicationDate = "1996-03-29",
                description = "description",
                image = "https://example.com/image333.jpg",
                link = "https://example.com/link333",
                price = "33.33",
            )
        )

        // When
        `when`(networkDataSource.searchBooks(title)).thenReturn(
            flowOf(PagingData.from(networkBooks))
        )

        // Then
        subject.searchBooks(title).collect { pagingData ->
            pagingData.map { book ->
                Assert.assertTrue(book.title.contains(title, ignoreCase = true))
            }
        }
    }

    @Test
    fun `fetchFavoriteBooks 책이 즐겨찾기 추가 되어 있으면 제거`() = runTest {

        // Given
        val favoritesBook = Book(
            isbn = "1234567890",
            title = "Android Developer",
            link = "https://example.com/link",
            imageUrl = "https://example.com/image.jpg",
            isFavorites = true
        )
        bookDao.insertBook(favoritesBook.asEntity(true))

        // When
        subject.toggleFavoriteBook(favoritesBook)

        // Then
        bookDao.fetchFavoriteBooks().first().map { book ->
            Assert.assertFalse(book.isbn == favoritesBook.isbn)
        }
    }

    @Test
    fun `toggleFavoriteBook 책이 즐겨찾기 되어 있지 않으면 추가`() = runTest {

        // Given
        val unFavoritesBook = Book(
            isbn = "0987654321",
            title = "Android Developer",
            link = "https://example.com/link",
            imageUrl = "https://example.com/image.jpg",
            isFavorites = false
        )

        // When
        subject.toggleFavoriteBook(unFavoritesBook)

        // Then
        bookDao.fetchFavoriteBooks().first().map { book ->
            Assert.assertTrue(book.isbn == unFavoritesBook.isbn)
        }

    }

    @Test
    fun `fetchFavoriteBooks 즐겨찾기 추가된 책을 반환`() = runTest {

        // Given
        val books = listOf(
            Book(
                isbn = "1234567890",
                title = "Android Developer",
                link = "https://example.com/link",
                imageUrl = "https://example.com/image.jpg",
                isFavorites = true
            ),
            Book(
                isbn = "0987654321",
                title = "Kotlin Developer",
                link = "https://example.com/link",
                imageUrl = "https://example.com/image.jpg",
                isFavorites = true
            ),
            Book(
                isbn = "1357924680",
                title = "Java Developer",
                link = "https://example.com/link",
                imageUrl = "https://example.com/image.jpg",
                isFavorites = false
            )
        )
        books.map { book -> bookDao.insertBook(book.asEntity(book.isFavorites)) }

        // When
        val fetchFavoriteBooks = subject.fetchFavoriteBooks().first()

        // Then
        Assert.assertFalse(fetchFavoriteBooks.size == books.size)
        Assert.assertNull(fetchFavoriteBooks.find { book -> !book.isFavorites })
    }

}