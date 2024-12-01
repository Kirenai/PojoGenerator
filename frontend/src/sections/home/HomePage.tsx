import { useNavigate } from 'react-router-dom'
import { Box, Container, Heading, Text, VStack } from '@chakra-ui/react'
import { Button } from '@/components/ui/button'
import Footer from '@/components/ui/footer'

const HomePage = () => {
  const navigate = useNavigate()

  const handleOnClick = () => {
    navigate('/pojo')
  }

  return (
    <>
      <Container py="16">
        <VStack gap="10">
          <VStack alignSelf="flex-start" maxW="3xl" gap="5" px="1.5">
            <Heading
              alignSelf="start"
              size={{ base: '4xl', md: '5xl' }}
              as="h1"
            >
              Pojo Generator <br /> herramienta para crear POJOs
            </Heading>
            <Text textStyle="2xl" color="gray.400" fontSize="xl">
              El principal objecto es facilitar el desarrollo al evitar la
              creación manual de clases Java para representar estructuras de
              datos complejas DTOs
            </Text>
          </VStack>
          <Box
            w="full"
            data-state="open"
            _open={{
              animationName: 'fade-in, scale-in',
              animationDuration: '300ms',
            }}
          >
            <Button
              onClick={() => handleOnClick()}
              variant="surface"
              colorPalette="teal"
              w={{ base: 'full', sm: '180px' }}
              size="xl"
            >
              Start Generator
            </Button>
          </Box>
        </VStack>
      </Container>
      <Footer />
    </>
  )
}

export default HomePage
